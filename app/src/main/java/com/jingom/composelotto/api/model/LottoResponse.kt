package com.jingom.composelotto.api.model

import com.squareup.moshi.Json

data class LottoResponse(
	@Json(name = "returnValue")
	val apiResult: String,
	@Json(name = "drwNoDate")
	val dayOfLottery: String,
	@Json(name = "totSellamnt")
	val totalSellAmount: Long,
	@Json(name = "firstWinamnt")
	val firstWinAmount: Long,
	@Json(name = "firstPrzwnerCo")
	val firstPrizeWinnerCount: Int,
	@Json(name = "firstAccumamnt")
	val firstAccumulatedAmount: Long,
	@Json(name = "drwtNo1")
	val no1: Int,
	@Json(name = "drwtNo2")
	val no2: Int,
	@Json(name = "drwtNo3")
	val no3: Int,
	@Json(name = "drwtNo4")
	val no4: Int,
	@Json(name = "drwtNo5")
	val no5: Int,
	@Json(name = "drwtNo6")
	val no6: Int,
	@Json(name = "bnusNo")
	val bonusNo: Int,
	@Json(name = "drwNo")
	val lotteryNo: Int
)