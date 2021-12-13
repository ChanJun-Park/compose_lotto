package com.jingom.composelotto.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jingom.composelotto.api.DHLottoApi
import com.jingom.composelotto.api.DHLottoApiService
import com.jingom.composelotto.api.model.LottoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(private val lottoApiService: DHLottoApiService) : ViewModel() {
	private val _lottoNumber = MutableLiveData<LottoResponse>()
	val lottoResponse: LiveData<LottoResponse>
		get() = _lottoNumber

	init {
		getLottoNumber()
	}

	private fun getLottoNumber() {
		viewModelScope.launch {
			val lottoResponse = withContext(Dispatchers.IO) {
				lottoApiService.getLottoNumber("getLottoNumber", 988)
			}

			_lottoNumber.value = if (lottoResponse.isSuccessful) {
				lottoResponse.body()
			} else {
				null
			}
		}
	}
}