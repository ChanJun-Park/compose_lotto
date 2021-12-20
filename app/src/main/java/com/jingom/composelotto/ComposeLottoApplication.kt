package com.jingom.composelotto

import android.app.Application
import com.jingom.composelotto.database.LottoDatabase
import com.jingom.composelotto.database.dao.LottoResultDao
import com.jingom.composelotto.network.NetworkConnectionLiveData
import timber.log.Timber

class ComposeLottoApplication : Application() {

	lateinit var networkConnectionLiveData: NetworkConnectionLiveData
	lateinit var lottoResultDao: LottoResultDao

	override fun onCreate() {
		super.onCreate()

		init()

//		runBackGroundSync()
	}

	private fun init() {
		Timber.plant(Timber.DebugTree())

		networkConnectionLiveData = NetworkConnectionLiveData(this)

		lottoResultDao = LottoDatabase.getInstance(this).lottoResultDao
	}

//	private fun runBackGroundSync() {
//		val workConstraint = Constraints.Builder()
//			.setRequiredNetworkType(NetworkType.CONNECTED)
//			.build()
//
//		val workRequest = OneTimeWorkRequestBuilder<LottoResultSyncWorker>().setConstraints(workConstraint).build()
//
//		WorkManager.getInstance(this).enqueue(workRequest)
//	}
}