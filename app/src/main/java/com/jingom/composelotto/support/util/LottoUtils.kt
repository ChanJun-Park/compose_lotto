package com.jingom.composelotto.support.util

import com.jingom.composelotto.datetime.LocalDateUtils
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters

object LottoUtils {
	private const val KNOWN_LOTTERY_NO = 993
	private val KNOWN_LOTTERY_DATE_OF_993 = LocalDate.of(2021, 12, 11)

	fun getLatestLottoNo(): Int {
		val latestLotteryDate = LocalDateUtils.todayInKorea().with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY))

		val term = ChronoUnit.WEEKS.between(KNOWN_LOTTERY_DATE_OF_993, latestLotteryDate)
		return KNOWN_LOTTERY_NO + term.toInt()
	}
}