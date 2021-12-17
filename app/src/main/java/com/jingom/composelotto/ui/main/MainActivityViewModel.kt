package com.jingom.composelotto.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.jingom.composelotto.api.NetworkConnectionLiveData
import com.jingom.composelotto.repository.LottoRepository
import com.jingom.composelotto.db.model.LottoResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainActivityViewModel(application: Application, private val lottoRepository: LottoRepository) : AndroidViewModel(application) {
	val networkConnectionLiveData = NetworkConnectionLiveData(application)

	private val _latestLottoResult = MutableLiveData<LottoResult?>()
	val latestLottoResult: LiveData<LottoResult?>
		get() = _latestLottoResult

	private var job: Job? = null

	fun getLastLottoNumber(isInternetAvailable: Boolean) {
		val prevJob = job
		if (prevJob != null && prevJob.isActive) {
			prevJob.cancel()
		}

		job = viewModelScope.launch {
			_latestLottoResult.value = withContext(Dispatchers.IO) {
				try {
					lottoRepository.getLastLottoResult(isInternetAvailable)
				} catch (e: Exception) {
					null
				}
			}
		}
	}
}