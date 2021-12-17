package com.jingom.composelotto.db.model

import androidx.annotation.VisibleForTesting
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.jingom.composelotto.api.model.DHLottoResponseBody

@VisibleForTesting
const val INVALID = -1

@Entity(tableName = "lotto_result", indices = [Index(value = ["lottery_no"], unique = true)])
data class LottoResult(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0,

	val day: String = "",

	@ColumnInfo(name = "total_sell_amount")
	val totalSellAmount: Long = INVALID.toLong(),

	@ColumnInfo(name = "first_win_amount")
	val firstWinAmount: Long = INVALID.toLong(),

	@ColumnInfo(name = "first_prize_winner_count")
	val firstPrizeWinnerCount: Int = INVALID,

	@ColumnInfo(name = "first_accumulated_amount")
	val firstAccumulatedAmount: Long = INVALID.toLong(),

	val no1: Int = INVALID,
	val no2: Int = INVALID,
	val no3: Int = INVALID,
	val no4: Int = INVALID,
	val no5: Int = INVALID,
	val no6: Int = INVALID,

	@ColumnInfo(name = "bonus_no")
	val bonusNo: Int = INVALID,

	@ColumnInfo(name = "lottery_no")
	val lotteryNo: Int = INVALID
) {
	companion object {
		fun from(dhLottoResponseBody: DHLottoResponseBody) = LottoResult(
			day = dhLottoResponseBody.dayOfLottery ?: "",
			totalSellAmount = dhLottoResponseBody.totalSellAmount,
			firstWinAmount = dhLottoResponseBody.firstWinAmount,
			firstPrizeWinnerCount = dhLottoResponseBody.firstPrizeWinnerCount,
			firstAccumulatedAmount = dhLottoResponseBody.firstAccumulatedAmount,
			no1 = dhLottoResponseBody.no1,
			no2 = dhLottoResponseBody.no2,
			no3 = dhLottoResponseBody.no3,
			no4 = dhLottoResponseBody.no4,
			no5 = dhLottoResponseBody.no5,
			no6 = dhLottoResponseBody.no6,
			bonusNo = dhLottoResponseBody.bonusNo,
			lotteryNo = dhLottoResponseBody.lotteryNo
		)
	}
}
