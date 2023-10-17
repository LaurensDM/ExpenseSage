package com.example.expensesage.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL =
    "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(
        Json.asConverterFactory("application/json".toMediaType())
    )
    .baseUrl(BASE_URL).build()

interface CurrencyApiService {
    @GET
    suspend fun getCurrencies(): JsonObject

    @Headers("Content-Type: application/json")
    @GET("eur.json")
    suspend fun getEurRate(): JsonObject

    @Headers("Content-Type: application/json")
    @GET("usd.json")
    suspend fun getUsdRate(): JsonObject

    @Headers("Content-Type: application/json")
    @GET("jpy.json")
    suspend fun getJpyRate(): JsonObject
}

object CurrencyApi {
    val retrofitService: CurrencyApiService by lazy {
        retrofit.create(CurrencyApiService::class.java)
    }
}