package com.jingom.composelotto

import com.jingom.composelotto.api.model.DHLotto

interface LottoRepository {
	suspend fun getLastLottoResponse(): DHLotto
}