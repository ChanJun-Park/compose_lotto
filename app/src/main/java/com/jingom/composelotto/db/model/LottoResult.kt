package com.jingom.composelotto.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

private const val INVALID = -1

@Entity(tableName = "lotto_result")
data class LottoResult(
	@PrimaryKey(autoGenerate = true)
	val id: Long,

	val day: String?,

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
)
