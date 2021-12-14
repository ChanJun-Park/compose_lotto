package com.jingom.composelotto

import com.jingom.composelotto.api.model.DHLottoResponseBody

interface LottoRepository {
	suspend fun getLastLottoResult(): DHLottoResponseBody
}