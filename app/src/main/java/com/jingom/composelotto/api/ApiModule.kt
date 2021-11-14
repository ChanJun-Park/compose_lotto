package com.jingom.composelotto.api

import com.jingom.composelotto.api.model.LottoResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.dhlottery.co.kr/"

private val moshi = Moshi.Builder()
	.add(KotlinJsonAdapterFactory())
	.build()

private val retrofit = Retrofit.Builder()
	.baseUrl(BASE_URL)
	.addConverterFactory(MoshiConverterFactory.create(moshi))
	.build()

interface DHLottoApiService {
	@GET("common.do")
	fun getLottoNumber(@Query("method") method: String, @Query("drwNo") drwNo: Int): Call<LottoResponse>
}

object DHLottoApi {
	val retrofitService: DHLottoApiService by lazy {
		retrofit.create(DHLottoApiService::class.java)
	}
}