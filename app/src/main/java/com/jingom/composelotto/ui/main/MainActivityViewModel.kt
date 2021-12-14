package com.jingom.composelotto.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jingom.composelotto.LottoRepository
import com.jingom.composelotto.api.model.DHLottoResponseBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(private val lottoRepository: LottoRepository) : ViewModel() {
	private val _lottoNumber = MutableLiveData<DHLottoResponseBody>()
	val lottoResult: LiveData<DHLottoResponseBody>
		get() = _lottoNumber

	init {
		getLastLottoNumber()
	}

	private fun getLastLottoNumber() {
		viewModelScope.launch {
			_lottoNumber.value = withContext(Dispatchers.IO) {
				lottoRepository.getLastLottoResult()
			}
		}
	}
}