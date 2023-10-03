package com.shdwraze.metro.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL =
    "https://backend-service-bizc.onrender.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface MetroApiService {
    @GET("stations")
    suspend fun getStations(@Query("city") city: String = "Харків"): List<Station>
}

object MetroApi {
    val retrofitService : MetroApiService by lazy {
        retrofit.create(MetroApiService::class.java)
    }
}