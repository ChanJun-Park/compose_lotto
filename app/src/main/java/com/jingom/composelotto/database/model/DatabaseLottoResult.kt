package com.jingom.composelotto.database.model

import androidx.annotation.VisibleForTesting
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.jingom.composelotto.network.model.NetworkLottoResult
import com.jingom.composelotto.ui.model.LottoResult

@VisibleForTesting
const val INVALID = -1

@Entity(tableName = "lotto_result")
data class DatabaseLottoResult(
	@PrimaryKey
	@ColumnInfo(name = "lottery_no")
	val lotteryNo: Int = INVALID,

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
	val bonusNo: Int = INVALID
)

fun DatabaseLottoResult.asDomainModel() = LottoResult(
	no1 = no1,
	no2 = no2,
	no3 = no3,
	no4 = no4,
	no5 = no5,
	no6 = no6,
	bonusNo = bonusNo,
	lotteryNo = lotteryNo,
	day = day
)