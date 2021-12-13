package com.jingom.composelotto.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jingom.composelotto.api.DHLottoApiService
import java.lang.IllegalArgumentException

@SuppressWarnings("unchecked")
class MainActivityViewModelFactory(private val lottoApiService: DHLottoApiService): ViewModelProvider.Factory {
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		return if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
			MainActivityViewModel(lottoApiService) as T
		} else {
			throw IllegalArgumentException()
		}
	}
}