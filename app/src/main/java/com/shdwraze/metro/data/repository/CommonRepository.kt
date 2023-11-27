package com.shdwraze.metro.data.repository

import com.shdwraze.metro.data.api.MetroApiService
import javax.inject.Inject

interface CommonRepository {
    suspend fun getLines(): List<String>

    suspend fun getCities(): List<String>
}

class NetworkCommonRepository @Inject constructor(
    private val metroApiService: MetroApiService
) : CommonRepository {
    override suspend fun getLines(): List<String> = metroApiService.getLines()

    override suspend fun getCities(): List<String> = metroApiService.getCities()
}