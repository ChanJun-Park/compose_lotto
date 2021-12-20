package com.jingom.composelotto.support.util

import com.jingom.composelotto.datetime.AppClickUtils
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Clock
import java.time.LocalDateTime
import java.time.ZoneOffset

class LottoUtilsTest {
	@Test
	fun getLatestLottoNoTest() {
		val testLocalDateTime = LocalDateTime.of(2021, 12, 20, 0, 0, 0)
		val testZoneOffset = ZoneOffset.UTC
		val testClock = Clock.fixed(testLocalDateTime.atOffset(testZoneOffset).toInstant(), testZoneOffset)

		AppClickUtils.setClock(testClock)

		assertEquals(994, LottoUtils.getLatestLottoNo())
	}
}