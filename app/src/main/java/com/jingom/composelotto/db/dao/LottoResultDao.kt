package com.jingom.composelotto.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jingom.composelotto.db.model.LottoResult

@Dao
interface LottoResultDao {
	@Insert
	suspend fun insert(lottoResult: LottoResult)

	@Update
	suspend fun update(lottoResult: LottoResult)

	@Query("SELECT * FROM lotto_result WHERE id = :id")
	suspend fun get(id: Long)

	@Query("SELECT * FROM lotto_result ORDER BY lottery_no DESC")
	suspend fun getAll()

	@Query("SELECT * FROM lotto_result ORDER BY lottery_no DESC LIMIT 1")
	suspend fun getLatest()

	@Query("DELETE FROM lotto_result")
	suspend fun clear()
}