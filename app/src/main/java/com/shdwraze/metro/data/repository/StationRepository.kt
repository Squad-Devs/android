package com.shdwraze.metro.data.repository

import com.shdwraze.metro.data.api.MetroApiService
import com.shdwraze.metro.data.api.result.ApiResult
import com.shdwraze.metro.data.api.result.ApiSafeCaller
import com.shdwraze.metro.data.model.Station
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface StationRepository {
    suspend fun getStations(city: String, line: String?): ApiResult<List<Station>>

    suspend fun getStationById(id: String): Station
}

class NetworkStationRepository @Inject constructor(
    private val metroApiService: MetroApiService,
    private val apiSafeCaller: ApiSafeCaller
) : StationRepository {
    override suspend fun getStations(city: String, line: String?): ApiResult<List<Station>> =
        apiSafeCaller.safeApiCall(Dispatchers.IO) {
            metroApiService.getStations()
        }

    override suspend fun getStationById(id: String): Station = metroApiService.getStationById(id)
}