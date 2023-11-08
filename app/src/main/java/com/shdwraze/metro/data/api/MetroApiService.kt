package com.shdwraze.metro.data.api

import com.shdwraze.metro.data.model.Station
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MetroApiService {
    @GET("stations")
    suspend fun getStations(@Query("city") city: String = "Харків"): List<Station>

    @GET("stations/{id}")
    suspend fun getStationById(@Path("id") id: String): Station
}