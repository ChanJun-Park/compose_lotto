package com.jingom.composelotto.network.model

import com.jingom.composelotto.database.model.DatabaseLottoResult
import com.squareup.moshi.Json

private const val INVALID = -1
const val SUCCESS_RESULT_STRING = "success"

data class NetworkLottoResult(
	@Json(name = "returnValue")
	val apiResult: String,
	@Json(name = "drwNoDate")
	val dayOfLottery: String?,
	@Json(name = "totSellamnt")
	val totalSellAmount: Long = INVALID.toLong(),
	@Json(name = "firstWinamnt")
	val firstWinAmount: Long = INVALID.toLong(),
	@Json(name = "firstPrzwnerCo")
	val firstPrizeWinnerCount: Int = INVALID,
	@Json(name = "firstAccumamnt")
	val firstAccumulatedAmount: Long = INVALID.toLong(),
	@Json(name = "drwtNo1")
	val no1: Int = INVALID,
	@Json(name = "drwtNo2")
	val no2: Int = INVALID,
	@Json(name = "drwtNo3")
	val no3: Int = INVALID,
	@Json(name = "drwtNo4")
	val no4: Int = INVALID,
	@Json(name = "drwtNo5")
	val no5: Int = INVALID,
	@Json(name = "drwtNo6")
	val no6: Int = INVALID,
	@Json(name = "bnusNo")
	val bonusNo: Int = INVALID,
	@Json(name = "drwNo")
	val lotteryNo: Int = INVALID
)

fun NetworkLottoResult.isFail() = !isSuccess()

fun NetworkLottoResult.isSuccess() = apiResult == SUCCESS_RESULT_STRING

fun NetworkLottoResult.asDatabaseModel() = DatabaseLottoResult(
	lotteryNo = lotteryNo,
	day = dayOfLottery ?: "",
	totalSellAmount = totalSellAmount,
	firstWinAmount = firstWinAmount,
	firstPrizeWinnerCount = firstPrizeWinnerCount,
	firstAccumulatedAmount = firstAccumulatedAmount,
	no1 = no1,
	no2 = no2,
	no3 = no3,
	no4 = no4,
	no5 = no5,
	no6 = no6,
	bonusNo = bonusNo,
)