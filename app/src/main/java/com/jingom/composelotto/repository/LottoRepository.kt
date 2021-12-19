package com.jingom.composelotto.repository

import com.jingom.composelotto.database.model.LottoResult

interface LottoRepository {
	suspend fun getLastLottoResult(isInternetAvailable: Boolean): LottoResult
}