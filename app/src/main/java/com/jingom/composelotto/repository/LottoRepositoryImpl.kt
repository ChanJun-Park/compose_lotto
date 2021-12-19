package com.jingom.composelotto.repository

import com.jingom.composelotto.network.DHLottoApiService
import com.jingom.composelotto.network.model.NetworkLottoResult
import com.jingom.composelotto.network.model.isFail
import com.jingom.composelotto.network.model.isSuccess
import com.jingom.composelotto.datetime.LocalDateUtils
import com.jingom.composelotto.database.dao.LottoResultDao
import com.jingom.composelotto.database.model.DatabaseLottoResult
import com.jingom.composelotto.support.util.LottoUtils
import retrofit2.Response

class LottoRepositoryImpl(
	private val lottoApiService: DHLottoApiService,
	private val lottoResultDao: LottoResultDao
) : LottoRepository {

	private lateinit var latestLottoResult: DatabaseLottoResult

	override suspend fun getLastLottoResult(isInternetAvailable: Boolean): DatabaseLottoResult {
		val lastLottoResultInDB = lottoResultDao.getLatest()

		if (!isInternetAvailable) {
			return lastLottoResultInDB ?: throw IllegalStateException("Not yet ready for offline cache")
		}

		if (lastLottoResultInDB != null && isLatestLottoResult(lastLottoResultInDB)) {
			latestLottoResult = lastLottoResultInDB
			return lastLottoResultInDB
		}

		var lottoResponse: Response<NetworkLottoResult>?
		var lastNetworkLottoResult: NetworkLottoResult? = null

		var lotteryNumber = lastLottoResultInDB?.lotteryNo ?: LottoUtils.KNOWN_LOTTERY_NO

		while (true) {
			lottoResponse = lottoApiService.getLottoNumber("getLottoNumber", lotteryNumber)
			if (isInvalidResponse(lottoResponse)) {
				break
			}

			lottoResponse.body()?.let {
				saveLottoResponse(it)

				lastNetworkLottoResult = it
				lotteryNumber++
			}
		}

		return convertToLottoResult(lastNetworkLottoResult)
	}

	private suspend fun saveLottoResponse(networkLottoResult: NetworkLottoResult) {
		if (networkLottoResult.isSuccess()) {
			lottoResultDao.insert(DatabaseLottoResult.from(networkLottoResult))
		}
	}

	@Throws(IllegalStateException::class)
	private fun convertToLottoResult(networkLottoResult: NetworkLottoResult?): DatabaseLottoResult {
		if (networkLottoResult == null || networkLottoResult.isFail()) {
			throw IllegalStateException()
		}

		return DatabaseLottoResult.from(networkLottoResult)
	}

	private fun isLatestLottoResult(latestLottoResultInDB: DatabaseLottoResult) = try {
		LocalDateUtils.todayInKorea() == LocalDateUtils.parseWithDrwPattern(latestLottoResultInDB.day)
	} catch (e: Exception) {
		false
	}

	private fun isInvalidResponse(response: Response<NetworkLottoResult>): Boolean {
		val lottoResponseBody = response.body()
		return (!response.isSuccessful || lottoResponseBody == null || lottoResponseBody.isFail())
	}
}