package com.jingom.composelotto.datetime

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Clock
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset

class LocalDateUtilsTest {
	@Test
	fun todayInKoreaTest() {
		val testLocalDateTime = LocalDateTime.of(2021, 12, 17, 15, 0, 0)
		val testZoneOffset = ZoneOffset.UTC
		val testClock = Clock.fixed(testLocalDateTime.atOffset(testZoneOffset).toInstant(), testZoneOffset)

		AppClickUtils.setClock(testClock)

		assertEquals(LocalDate.of(2021, 12, 18), LocalDateUtils.todayInKorea())
	}

	@Test
	fun parseWithDrwPatternTest() {
		val drwDateString = "2021-12-18"
		val parsedLocalDate = LocalDateUtils.parseWithDrwPattern(drwDateString)

		assertEquals(LocalDate.of(2021, 12, 18), parsedLocalDate)
	}

	@Test
	fun formatWithDrwPatternTest() {
		val localDate = LocalDate.of(2021, 12, 18)

		assertEquals(localDate.formatWithDrwPattern(), "2021-12-18")
	}
}