package com.jingom.composelotto.repository

import com.jingom.composelotto.database.model.DatabaseLottoResult

interface LottoRepository {
	suspend fun getLastLottoResult(isInternetAvailable: Boolean): DatabaseLottoResult
}