package com.jingom.composelotto.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jingom.composelotto.repository.LottoRepository

@SuppressWarnings("unchecked")
class MainActivityViewModelFactory(private val application: Application,  private val lottoRepository: LottoRepository) : ViewModelProvider.AndroidViewModelFactory(application) {
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		return if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
			MainActivityViewModel(application, lottoRepository) as T
		} else {
			throw IllegalArgumentException()
		}
	}
}