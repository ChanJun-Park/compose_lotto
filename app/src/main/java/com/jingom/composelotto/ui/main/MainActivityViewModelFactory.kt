package com.jingom.composelotto.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jingom.composelotto.LottoRepository

@SuppressWarnings("unchecked")
class MainActivityViewModelFactory(private val lottoRepository: LottoRepository) : ViewModelProvider.Factory {
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		return if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
			MainActivityViewModel(lottoRepository) as T
		} else {
			throw IllegalArgumentException()
		}
	}
}