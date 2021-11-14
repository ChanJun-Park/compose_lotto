package com.jingom.composelotto.api.model

data class LottoResponse(
	val returnValue: String,
	val drwNoDate: String,
	val totSellamnt: Long,
	val firstWinamnt: Long,
	val firstPrzwnerCo: Int,
	val firstAccumamnt: Long,
	val drwtNo1: Int,
	val drwtNo2: Int,
	val drwtNo3: Int,
	val drwtNo4: Int,
	val drwtNo5: Int,
	val drwtNo6: Int,
	val bnusNo: Int,
	val drwNo: Int
)