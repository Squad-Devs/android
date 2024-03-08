package com.shdwraze.metro.data.repository

import com.shdwraze.metro.data.api.MetroApiService
import com.shdwraze.metro.data.api.result.ApiResult
import com.shdwraze.metro.data.api.result.ApiSafeCaller
import com.shdwraze.metro.data.model.Metropolitan
import com.shdwraze.metro.data.model.ShortestPath
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface MetroRepository {
    suspend fun getLines(): List<String>

    suspend fun getCities(): List<String>

    suspend fun getMetropolitan(city: String): ApiResult<Metropolitan>

    suspend fun getShortestPath(from: Int, to: Int): ApiResult<ShortestPath>
}

class NetworkMetroRepository @Inject constructor(
    private val metroApiService: MetroApiService,
    private val apiSafeCaller: ApiSafeCaller
) : MetroRepository {
    override suspend fun getLines(): List<String> = metroApiService.getLines()

    override suspend fun getCities(): List<String> = metroApiService.getCities()

    override suspend fun getMetropolitan(city: String): ApiResult<Metropolitan> {
        return apiSafeCaller.safeApiCall(Dispatchers.IO) {
            metroApiService.getMetropolitan(city)
        }
    }

    override suspend fun getShortestPath(from: Int, to: Int): ApiResult<ShortestPath> {
        return apiSafeCaller.safeApiCall(Dispatchers.IO) {
            metroApiService.getShortestPath(from, to)
        }
    }
}