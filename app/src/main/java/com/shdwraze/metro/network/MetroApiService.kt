package com.shdwraze.metro.network

import retrofit2.http.GET
import retrofit2.http.Query

interface MetroApiService {
    @GET("stations")
    suspend fun getStations(@Query("city") city: String = "Харків"): List<Station>
}