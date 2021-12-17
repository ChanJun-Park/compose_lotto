package com.jingom.composelotto.datetime

import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object LocalDateUtils {
	fun todayInKorea(): LocalDate = ZonedDateTime.now(AppClickUtils.getClock()).withZoneSameInstant(KOREA_TIMEZONE_ID).toLocalDate()

	fun parseWithDrwPattern(dateString: String): LocalDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(DateTimeFormat.DRW_NO_DATE_PATTERN))
}

fun LocalDate.formatWithDrwPattern(): String = DateTimeFormatter.ofPattern(DateTimeFormat.DRW_NO_DATE_PATTERN).format(this)
