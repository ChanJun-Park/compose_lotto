package com.jingom.composelotto.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.jingom.composelotto.ui.lotto.LotteryResult
import com.jingom.composelotto.ui.theme.ComposeLottoTheme

class MainActivity : ComponentActivity() {

	private val viewModel: MainActivityViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent { ComposeLotto() }
	}

	@Composable
	private fun ComposeLotto() {
		ComposeLottoTheme {
			val lottoNumber by viewModel.lottoResponse.observeAsState()

			lottoNumber?.let {
				LotteryResult(response = it)
			}
		}
	}
}