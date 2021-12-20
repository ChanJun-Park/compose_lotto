package com.jingom.composelotto.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.jingom.composelotto.network.NetworkConnectionLiveData
import com.jingom.composelotto.repository.LottoRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application, private val lottoRepository: LottoRepository) : AndroidViewModel(application) {
	val networkConnectionLiveData = NetworkConnectionLiveData(application)

	val latestLottoResult = lottoRepository.latestLottoResult

	fun refreshLatestLottoResult() {
		viewModelScope.launch {
			lottoRepository.refreshLatestLottoResult()
		}
	}

	class Factory(private val application: Application,  private val lottoRepository: LottoRepository) : ViewModelProvider.AndroidViewModelFactory(application) {
		override fun <T : ViewModel?> create(modelClass: Class<T>): T {
			return if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
				@Suppress("UNCHECKED_CAST")
				MainActivityViewModel(application, lottoRepository) as T
			} else {
				throw IllegalArgumentException()
			}
		}
	}
}