package com.jingom.composelotto.ui.util

import androidx.compose.ui.graphics.Color
import com.jingom.composelotto.ui.theme.*

object LottoNumberUtil {

	const val LOTTO_BALL_COUNT = 6

	private val lottoNumberColor = arrayListOf(LottoBallYellow, LottoBallBlue, LottoBallRed, LottoBallGray, LottoBallGreen)

	fun getLottoBallColor(number: Int): Color {
		val index = (number - 1) / 10

		if (index < 0 || index >= lottoNumberColor.size) {
			throw IllegalArgumentException("Invalid Lotto Number: $number, index : $index")
		}

		return lottoNumberColor[index]
	}
}