package com.jingom.composelotto.database.model

import androidx.annotation.VisibleForTesting
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.jingom.composelotto.network.model.NetworkLottoResult

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
) {
	companion object {
		fun from(networkLottoResult: NetworkLottoResult) = DatabaseLottoResult(
			lotteryNo = networkLottoResult.lotteryNo,
			day = networkLottoResult.dayOfLottery ?: "",
			totalSellAmount = networkLottoResult.totalSellAmount,
			firstWinAmount = networkLottoResult.firstWinAmount,
			firstPrizeWinnerCount = networkLottoResult.firstPrizeWinnerCount,
			firstAccumulatedAmount = networkLottoResult.firstAccumulatedAmount,
			no1 = networkLottoResult.no1,
			no2 = networkLottoResult.no2,
			no3 = networkLottoResult.no3,
			no4 = networkLottoResult.no4,
			no5 = networkLottoResult.no5,
			no6 = networkLottoResult.no6,
			bonusNo = networkLottoResult.bonusNo,
		)
	}
}
