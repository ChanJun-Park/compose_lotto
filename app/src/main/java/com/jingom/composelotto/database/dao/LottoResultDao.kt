package com.jingom.composelotto.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.jingom.composelotto.database.model.DatabaseLottoResult

@Dao
interface LottoResultDao {
	@Insert(onConflict = REPLACE)
	suspend fun insert(lottoResult: DatabaseLottoResult): Long

	@Update
	suspend fun update(lottoResult: DatabaseLottoResult): Int

	@Query("SELECT * FROM lotto_result WHERE lottery_no = :lotteryNo")
	suspend fun get(lotteryNo: Int): DatabaseLottoResult?

	@Query("SELECT * FROM lotto_result ORDER BY lottery_no DESC")
	suspend fun getAll(): List<DatabaseLottoResult>

	@Query("SELECT * FROM lotto_result ORDER BY lottery_no DESC LIMIT 1")
	suspend fun getLatest(): DatabaseLottoResult?

	@Query("SELECT lottery_no FROM lotto_result ORDER BY lottery_no ASC LIMIT 1")
	suspend fun getFirstLotteryNoInDB(): Int?

	@Query("SELECT lottery_no FROM lotto_result ORDER BY lottery_no DESC LIMIT 1")
	suspend fun getLastLotteryNoInDB(): Int?

	@Query("DELETE FROM lotto_result")
	suspend fun clear()
}