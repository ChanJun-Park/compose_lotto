package com.jingom.composelotto

import com.jingom.composelotto.api.DHLottoApiService
import com.jingom.composelotto.api.model.DHLottoResponseBody
import com.jingom.composelotto.api.model.SUCCESS_RESULT_STRING
import com.jingom.composelotto.support.util.LottoUtils
import retrofit2.Response

class LottoRepositoryImpl(private val lottoApiService: DHLottoApiService): LottoRepository {

	private lateinit var lastDHLottoResponseBody: DHLottoResponseBody

	override suspend fun getLastLottoResult(): DHLottoResponseBody {
		var lottoResponse: Response<DHLottoResponseBody>?
		var lotteryNumber = LottoUtils.KNOWN_LOTTERY_NO

		while(true) {
			lottoResponse = lottoApiService.getLottoNumber("getLottoNumber", lotteryNumber)
			if (isInvalidResponse(lottoResponse)) {
				break
			}

			lottoResponse.body()?.let {
				lastDHLottoResponseBody = it
				lotteryNumber++
			}
		}

		return lastDHLottoResponseBody
	}

	private fun isInvalidResponse(response: Response<DHLottoResponseBody>): Boolean {
		val lottoResponseBody = response.body()
		return (!response.isSuccessful || lottoResponseBody == null || lottoResponseBody.apiResult != SUCCESS_RESULT_STRING)
	}
}