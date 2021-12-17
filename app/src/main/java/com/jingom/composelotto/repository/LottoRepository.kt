package com.jingom.composelotto.repository

import com.jingom.composelotto.db.model.LottoResult

interface LottoRepository {
	suspend fun getLastLottoResult(): LottoResult
}