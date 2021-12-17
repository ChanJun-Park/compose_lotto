package com.jingom.composelotto.datetime

import androidx.annotation.VisibleForTesting
import java.time.Clock
import java.time.ZoneId

const val KOREA_TIMEZONE_ID_STRING = "Asia/Seoul"
const val GMT_TIMEZONE_ID_STRING = "GMT"

val KOREA_TIMEZONE_ID = ZoneId.of(KOREA_TIMEZONE_ID_STRING)
val GMT_TIMEZONE_ID = ZoneId.of(GMT_TIMEZONE_ID_STRING)

object AppClickUtils {
	private var clock: Clock = Clock.systemDefaultZone()

	@JvmStatic
	fun getClock(): Clock = clock

	@VisibleForTesting
	fun setClock(clock: Clock) {
		this.clock = clock
	}
}