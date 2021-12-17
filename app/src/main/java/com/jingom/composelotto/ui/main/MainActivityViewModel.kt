package com.jingom.composelotto.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jingom.composelotto.repository.LottoRepository
import com.jingom.composelotto.db.model.LottoResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(private val lottoRepository: LottoRepository) : ViewModel() {
	private val _latestLottoResult = MutableLiveData<LottoResult>()
	val latestLottoResult: LiveData<LottoResult>
		get() = _latestLottoResult

	init {
		getLastLottoNumber()
	}

	private fun getLastLottoNumber() {
		viewModelScope.launch {
			_latestLottoResult.value = withContext(Dispatchers.IO) {
				lottoRepository.getLastLottoResult()
			}
		}
	}
}