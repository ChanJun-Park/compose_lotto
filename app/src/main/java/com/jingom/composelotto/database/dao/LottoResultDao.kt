package com.jingom.composelotto.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import androidx.room.Update
import com.jingom.composelotto.database.model.DatabaseLottoResult

@Dao
interface LottoResultDao {
	@Insert(onConflict = IGNORE)
	suspend fun insert(lottoResult: DatabaseLottoResult): Long

	@Update
	suspend fun update(lottoResult: DatabaseLottoResult): Int

	@Query("SELECT * FROM lotto_result WHERE id = :id")
	suspend fun get(id: Long): DatabaseLottoResult?

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