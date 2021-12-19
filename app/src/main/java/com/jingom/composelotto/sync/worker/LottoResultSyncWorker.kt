package com.jingom.composelotto.sync.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.jingom.composelotto.network.DHLottoApi
import com.jingom.composelotto.network.DHLottoApiService
import com.jingom.composelotto.network.model.NetworkLottoResult
import com.jingom.composelotto.network.model.isFail
import com.jingom.composelotto.network.model.isSuccess
import com.jingom.composelotto.database.LottoDatabase
import com.jingom.composelotto.database.model.DatabaseLottoResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LottoResultSyncWorker(context: Context, workerParameters: WorkerParameters): CoroutineWorker(context, workerParameters) {

	private val lottoResultDao = LottoDatabase.getInstance(applicationContext).lottoResultDao
	private val lottoApiService: DHLottoApiService = DHLottoApi.retrofitService

	override suspend fun doWork(): Result {
		val firstLotteryNoInDB = withContext(Dispatchers.IO) {
			lottoResultDao.getFirstLotteryNoInDB()
		}

		if (firstLotteryNoInDB == null || firstLotteryNoInDB != 1) {
			var lottoResponse: Response<NetworkLottoResult>?

			var lotteryNumber = 1

			while (true) {
				lottoResponse = lottoApiService.getLottoNumber("getLottoNumber", lotteryNumber)
				if (isInvalidResponse(lottoResponse)) {
					break
				}

				lottoResponse.body()?.let {
					saveLottoResponse(it)

					lotteryNumber++
				}
			}
		}

		return Result.success()
	}

	private fun isInvalidResponse(response: Response<NetworkLottoResult>): Boolean {
		val lottoResponseBody = response.body()
		return (!response.isSuccessful || lottoResponseBody == null || lottoResponseBody.isFail())
	}

	private suspend fun saveLottoResponse(networkLottoResult: NetworkLottoResult) {
		if (networkLottoResult.isSuccess()) {
			lottoResultDao.insert(DatabaseLottoResult.from(networkLottoResult))
		}
	}
}