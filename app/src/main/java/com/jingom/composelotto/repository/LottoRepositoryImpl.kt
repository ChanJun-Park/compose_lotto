package com.jingom.composelotto.repository

import com.jingom.composelotto.api.DHLottoApiService
import com.jingom.composelotto.api.model.DHLottoResponseBody
import com.jingom.composelotto.api.model.isFail
import com.jingom.composelotto.api.model.isSuccess
import com.jingom.composelotto.datetime.LocalDateUtils
import com.jingom.composelotto.db.dao.LottoResultDao
import com.jingom.composelotto.db.model.LottoResult
import com.jingom.composelotto.support.util.LottoUtils
import retrofit2.Response

class LottoRepositoryImpl(
	private val lottoApiService: DHLottoApiService,
	private val lottoResultDao: LottoResultDao
) : LottoRepository {

	private lateinit var latestLottoResult: LottoResult

	override suspend fun getLastLottoResult(): LottoResult {
		val lastLottoResultInDB = lottoResultDao.getLatest()

		if (lastLottoResultInDB != null && isLatestLottoResult(lastLottoResultInDB)) {
			latestLottoResult = lastLottoResultInDB
			return lastLottoResultInDB
		}

		var lottoResponse: Response<DHLottoResponseBody>?
		var lastDHLottoResponseBody: DHLottoResponseBody? = null

		var lotteryNumber = lastLottoResultInDB?.lotteryNo ?: LottoUtils.KNOWN_LOTTERY_NO

		while (true) {
			lottoResponse = lottoApiService.getLottoNumber("getLottoNumber", lotteryNumber)
			if (isInvalidResponse(lottoResponse)) {
				break
			}

			lottoResponse.body()?.let {
				saveLottoResponse(it)

				lastDHLottoResponseBody = it
				lotteryNumber++
			}
		}

		return convertToLottoResult(lastDHLottoResponseBody)
	}

	private suspend fun saveLottoResponse(dhLottoResponseBody: DHLottoResponseBody) {
		if (dhLottoResponseBody.isSuccess()) {
			lottoResultDao.insert(LottoResult.from(dhLottoResponseBody))
		}
	}

	@Throws(IllegalStateException::class)
	private fun convertToLottoResult(dhLottoResponseBody: DHLottoResponseBody?): LottoResult {
		if (dhLottoResponseBody == null || dhLottoResponseBody.isFail()) {
			throw IllegalStateException()
		}

		return LottoResult.from(dhLottoResponseBody)
	}

	private fun isLatestLottoResult(latestLottoResultInDB: LottoResult) = try {
		LocalDateUtils.todayInKorea() == LocalDateUtils.parseWithDrwPattern(latestLottoResultInDB.day)
	} catch (e: Exception) {
		false
	}

	private fun isInvalidResponse(response: Response<DHLottoResponseBody>): Boolean {
		val lottoResponseBody = response.body()
		return (!response.isSuccessful || lottoResponseBody == null || lottoResponseBody.isFail())
	}
}