package com.shdwraze.metro.data.repository

import com.shdwraze.metro.data.api.MetroApiService
import com.shdwraze.metro.data.model.Station
import javax.inject.Inject

interface StationRepository {
    suspend fun getStations(): List<Station>
}

class NetworkStationRepository @Inject constructor(
    private val metroApiService: MetroApiService
) : StationRepository {
    override suspend fun getStations(): List<Station> = metroApiService.getStations()
}