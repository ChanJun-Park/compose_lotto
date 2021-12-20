package com.jingom.composelotto.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jingom.composelotto.network.DHLottoApiService
import com.jingom.composelotto.network.model.NetworkLottoResult
import com.jingom.composelotto.network.model.isFail
import com.jingom.composelotto.network.model.isSuccess
import com.jingom.composelotto.datetime.LocalDateUtils
import com.jingom.composelotto.database.dao.LottoResultDao
import com.jingom.composelotto.database.model.DatabaseLottoResult
import com.jingom.composelotto.database.model.asDomainModel
import com.jingom.composelotto.network.model.asDatabaseModel
import com.jingom.composelotto.support.util.LottoUtils
import com.jingom.composelotto.ui.model.LottoResult
import retrofit2.Response

class LottoRepositoryImpl(
	private val lottoApiService: DHLottoApiService,
	private val lottoResultDao: LottoResultDao
) : LottoRepository {

	val latestLottoResult: LiveData<LottoResult> = Transformations.map(lottoResultDao.getLatest()) {
		it.asDomainModel()
	}

	override suspend fun refreshLatestLottoResult() {
		val latestLottoNo = LottoUtils.getLatestLottoNo()
		val lottoResponseBody = lottoApiService.getLottoNumber("getLottoNumber", latestLottoNo).body()

		if (lottoResponseBody == null || lottoResponseBody.isFail()) {
			return
		}

		lottoResultDao.insert(lottoResponseBody.asDatabaseModel())
	}
}