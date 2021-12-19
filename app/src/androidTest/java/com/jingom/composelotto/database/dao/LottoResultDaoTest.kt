package com.jingom.composelotto.database.dao

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.jingom.composelotto.database.LottoDatabase
import com.jingom.composelotto.database.model.DatabaseLottoResult
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class LottoResultDaoTest {

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
		for (index in 1..10) {
			val lottoResult = createDummyResult().copy(lotteryNo = index)

			lottoResultDao.insert(lottoResult)
		}

		val dbLottoResult = lottoResultDao.get(id = 5)

		assertEquals(createDummyResult().copy(id = 5, lotteryNo = 5), dbLottoResult)
	}

	@Test
	@Throws(Exception::class)
	fun insertAndGetLatestLottoResult() = runBlocking {
		for (index in 1..10) {
			val lottoResult = createDummyResult().copy(lotteryNo = index)

			lottoResultDao.insert(lottoResult)
		}

		val dbLottoResult = lottoResultDao.getLatest()
		assertEquals(createDummyResult().copy(id = 10, lotteryNo = 10), dbLottoResult)
	}

	@Test
	@Throws(Exception::class)
	fun updateTest() = runBlocking {
		val lottoResult = createDummyResult().copy(lotteryNo = 5)

		val insertedItemId = lottoResultDao.insert(lottoResult)

		assertEquals(lottoResult.copy(id = insertedItemId), lottoResultDao.get(insertedItemId))

		val modifiedResult = lottoResult.copy(id = insertedItemId, lotteryNo = 7)

		lottoResultDao.update(modifiedResult)

		assertEquals(modifiedResult, lottoResultDao.get(insertedItemId))
	}

	@Test
	@Throws(Exception::class)
	fun getAllTest() = runBlocking {
		val targetResultList = mutableListOf<DatabaseLottoResult>()

		for (index in 1..10) {
			val lottoResult = createDummyResult().copy(id = index.toLong(), lotteryNo = index)

			targetResultList += lottoResult
			lottoResultDao.insert(lottoResult)
		}

		targetResultList.reverse()
		assertEquals(targetResultList, lottoResultDao.getAll())
	}

	@Test
	@Throws(Exception::class)
	fun clearTest() = runBlocking {
		for (index in 1..10) {
			val lottoResult = createDummyResult().copy(lotteryNo = index)

			lottoResultDao.insert(lottoResult)
		}

		assertEquals(10, lottoResultDao.getAll().size)

		lottoResultDao.clear()

		assertEquals(0, lottoResultDao.getAll().size)
	}

	private fun createDummyResult(): DatabaseLottoResult = DatabaseLottoResult(
		no1 = 1,
		no2 = 2,
		no3 = 3,
		no4 = 4,
		no5 = 5,
		no6 = 6,
		bonusNo = 7,
	)

}