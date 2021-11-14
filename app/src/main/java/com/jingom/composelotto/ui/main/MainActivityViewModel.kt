package com.jingom.composelotto.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jingom.composelotto.api.DHLottoApi
import com.jingom.composelotto.api.model.LottoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
	private val _lottoNumber = MutableLiveData<LottoResponse>()
	val lottoResponse: LiveData<LottoResponse>
		get() = _lottoNumber

	init {
		getLottoNumber()
	}

	private fun getLottoNumber() {
		DHLottoApi.retrofitService.getLottoNumber("getLottoNumber", 988).enqueue(object : Callback<LottoResponse> {
			override fun onFailure(call: Call<LottoResponse>, t: Throwable) {
				_lottoNumber.value = null
			}

			override fun onResponse(call: Call<LottoResponse>, response: Response<LottoResponse>) {
				response.body()?.let {
					_lottoNumber.value = it
				}
			}
		})
	}
}