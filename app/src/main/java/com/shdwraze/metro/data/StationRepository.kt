package com.shdwraze.metro.data

import com.shdwraze.metro.network.MetroApiService
import com.shdwraze.metro.network.Station

interface StationRepository {
    suspend fun getStations(): List<Station>
}

class NetworkStationRepository(
    private val metroApiService: MetroApiService
) : StationRepository {
    override suspend fun getStations(): List<Station> = metroApiService.getStations()
}