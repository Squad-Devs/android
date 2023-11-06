package com.shdwraze.metro.data.container

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.shdwraze.metro.data.repository.NetworkStationRepository
import com.shdwraze.metro.data.repository.StationRepository
import com.shdwraze.metro.data.api.MetroApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

//interface AppContainer {
//    val stationRepository: StationRepository
//}
//
//class DefaultAppContainer : AppContainer {
//
//    override val stationRepository: StationRepository by lazy {
//        NetworkStationRepository(retrofitService)
//    }
//
//    private val baseUrl =
//        "https://backend-service-bizc.onrender.com"
//
//    private val retrofit = Retrofit.Builder()
//        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
//        .baseUrl(baseUrl)
//        .build()
//
//    private val retrofitService : MetroApiService by lazy {
//        retrofit.create(MetroApiService::class.java)
//    }
//}