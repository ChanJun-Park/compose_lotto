package com.jingom.composelotto

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.jingom.composelotto.db.LottoDatabase
import com.jingom.composelotto.db.dao.LottoResultDao
import com.jingom.composelotto.db.model.INVALID
import com.jingom.composelotto.db.model.LottoResult
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class LottoDatabaseTest {

	private lateinit var db: LottoDatabase
	private lateinit var lottoResultDao: LottoResultDao

	@Before
	fun createDb() {
		val context = InstrumentationRegistry.getInstrumentation().targetContext
		db = Room.inMemoryDatabaseBuilder(context, LottoDatabase::class.java)
			.allowMainThreadQueries()
			.build()
		lottoResultDao = db.lottoResultDao
	}

	@After
	@Throws(IOException::class)
	fun closeDb() {
		db.close()
	}

	@Test
	@Throws(Exception::class)
	fun insertAndGetLottoResult() = runBlocking {
		val lottoResult = LottoResult(
			day = "",
			totalSellAmount = INVALID.toLong(),
			firstWinAmount = INVALID.toLong(),
			firstPrizeWinnerCount = INVALID,
			firstAccumulatedAmount = INVALID.toLong(),
			no1 = 1,
			no2 = 2,
			no3 = 3,
			no4 = 4,
			no5 = 5,
			no6 = 6,
			bonusNo = 7,
			lotteryNo = 1
		)

		lottoResultDao.insert(lottoResult)
		val dbLottoResult = lottoResultDao.getLatest()

		assertEquals(lottoResult.copy(id = 1), dbLottoResult)
	}

}