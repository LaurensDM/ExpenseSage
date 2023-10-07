package com.example.expensesage.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://random.imagecdn.app"
private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory
        .create())
        .baseUrl(BASE_URL).build()

interface ImageApiService {
    @GET
    suspend fun getImage(): String
}

object ImageApi {
    val retrofitService : ImageApiService by lazy {
        retrofit.create(ImageApiService::class.java)
    }
}