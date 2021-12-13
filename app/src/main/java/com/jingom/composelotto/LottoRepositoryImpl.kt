package com.jingom.composelotto

import com.jingom.composelotto.api.DHLottoApiService
import com.jingom.composelotto.api.model.DHLotto
import com.jingom.composelotto.api.model.SUCCESS_RESULT_STRING
import com.jingom.composelotto.support.util.LottoUtils
import retrofit2.Response

class LottoRepositoryImpl(private val lottoApiService: DHLottoApiService): LottoRepository {

	private lateinit var lastDHLotto: DHLotto

	override suspend fun getLastLottoResponse(): DHLotto {
		var lottoResponse: Response<DHLotto>?
		var lotteryNumber = LottoUtils.KNOWN_LOTTERY_NO

		while(true) {
			lottoResponse = lottoApiService.getLottoNumber("getLottoNumber", lotteryNumber)
			if (isInvalidResponse(lottoResponse)) {
				break
			}

			lottoResponse.body()?.let {
				lastDHLotto = it
				lotteryNumber++
			}
		}

		return lastDHLotto
	}

	private fun isInvalidResponse(response: Response<DHLotto>): Boolean {
		val lottoResult = response.body()
		return (!response.isSuccessful || lottoResult == null || lottoResult.apiResult != SUCCESS_RESULT_STRING)
	}
}