package com.jingom.composelotto.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jingom.composelotto.database.dao.LottoResultDao
import com.jingom.composelotto.database.model.LottoResult

@Database(entities = [LottoResult::class], version = 1, exportSchema = false)
abstract class LottoDatabase : RoomDatabase() {

	abstract val lottoResultDao: LottoResultDao

	companion object {

		@Volatile
		private var INSTANCE: LottoDatabase? = null

		fun getInstance(context: Context): LottoDatabase {
			synchronized(this) {
				var instance = INSTANCE

				if (instance == null) {
					instance = Room.databaseBuilder(
						context.applicationContext,
						LottoDatabase::class.java,
						"lotto_database"
					)
						.fallbackToDestructiveMigration()
						.build()

					INSTANCE = instance
				}

				return instance
			}
		}
	}
}