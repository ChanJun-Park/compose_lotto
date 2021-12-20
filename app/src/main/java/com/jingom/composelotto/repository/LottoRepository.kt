package com.jingom.composelotto.repository

import com.jingom.composelotto.database.model.DatabaseLottoResult

interface LottoRepository {
	suspend fun refreshLatestLottoResult()
//	suspend fun getLastLottoResult(isInternetAvailable: Boolean): DatabaseLottoResult
}