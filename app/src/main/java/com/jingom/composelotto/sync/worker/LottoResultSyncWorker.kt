package com.jingom.composelotto.sync.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.jingom.composelotto.ComposeLottoApplication
import com.jingom.composelotto.api.DHLottoApi
import com.jingom.composelotto.api.DHLottoApiService
import com.jingom.composelotto.api.model.DHLottoResponseBody
import com.jingom.composelotto.api.model.isFail
import com.jingom.composelotto.api.model.isSuccess
import com.jingom.composelotto.db.LottoDatabase
import com.jingom.composelotto.db.model.LottoResult
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
			var lottoResponse: Response<DHLottoResponseBody>?

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

	private fun isInvalidResponse(response: Response<DHLottoResponseBody>): Boolean {
		val lottoResponseBody = response.body()
		return (!response.isSuccessful || lottoResponseBody == null || lottoResponseBody.isFail())
	}

	private suspend fun saveLottoResponse(dhLottoResponseBody: DHLottoResponseBody) {
		if (dhLottoResponseBody.isSuccess()) {
			lottoResultDao.insert(LottoResult.from(dhLottoResponseBody))
		}
	}
}