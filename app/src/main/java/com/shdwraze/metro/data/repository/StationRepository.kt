package com.shdwraze.metro.data.repository

import com.shdwraze.metro.data.api.MetroApiService
import com.shdwraze.metro.data.model.Station
import javax.inject.Inject

interface StationRepository {
    suspend fun getStations(city: String, line: String?): List<Station>

    suspend fun getStationById(id: String): Station
}

class NetworkStationRepository @Inject constructor(
    private val metroApiService: MetroApiService
) : StationRepository {
    override suspend fun getStations(city: String, line: String?): List<Station> =
        metroApiService.getStations(city, line)

    override suspend fun getStationById(id: String): Station = metroApiService.getStationById(id)
}