package com.shdwraze.metro.data.api

import com.shdwraze.metro.common.Constants.DEFAULT_CITY
import com.shdwraze.metro.data.model.Metropolitan
import com.shdwraze.metro.data.model.Station
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MetroApiService {
    @GET("stations")
    suspend fun getStations(@Query("city") city: String = DEFAULT_CITY,
                            @Query("line") line: String? = null): List<Station>

    @GET("stations/{id}")
    suspend fun getStationById(@Path("id") id: Int): Station

    @GET("lines")
    suspend fun getLines(@Query("city") city: String = DEFAULT_CITY): List<String>

    @GET("cities")
    suspend fun getCities(): List<String>

    @GET("metropolitan")
    suspend fun getMetropolitan(@Query("city") city: String = DEFAULT_CITY) : Metropolitan
}