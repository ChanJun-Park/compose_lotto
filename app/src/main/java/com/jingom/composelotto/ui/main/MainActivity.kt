package com.jingom.composelotto.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModelProvider
import com.jingom.composelotto.repository.LottoRepositoryImpl
import com.jingom.composelotto.api.DHLottoApi
import com.jingom.composelotto.db.LottoDatabase
import com.jingom.composelotto.ui.lotto.LotteryResult
import com.jingom.composelotto.ui.theme.ComposeLottoTheme

class MainActivity : ComponentActivity() {

	private lateinit var viewModel: MainActivityViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent { ComposeLotto() }

		initViewModel()

		observeEvent()
	}

	@Composable
	private fun ComposeLotto() {
		ComposeLottoTheme {
			val lottoNumber by viewModel.latestLottoResult.observeAsState()

			lottoNumber?.let {
				LotteryResult(lottoResult = it)
			}
		}
	}

	private fun initViewModel() {
		val lottoRepository = LottoRepositoryImpl(DHLottoApi.retrofitService, LottoDatabase.getInstance(this).lottoResultDao)

		val viewModelFactory = MainActivityViewModelFactory(application, lottoRepository)

		viewModel = ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)
	}

	private fun observeEvent() {
		viewModel.networkConnectionLiveData.observe(this) { isConnected ->
			viewModel.getLastLottoNumber(isConnected)
		}
	}
}