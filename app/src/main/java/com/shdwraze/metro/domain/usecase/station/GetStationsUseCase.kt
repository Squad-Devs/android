package com.shdwraze.metro.domain.usecase.station

import com.shdwraze.metro.common.Constants.DELAY_FOR_USE_CASE
import com.shdwraze.metro.data.api.result.ApiResult
import com.shdwraze.metro.data.model.Station
import com.shdwraze.metro.data.repository.StationRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetStationsUseCase @Inject constructor(
    private val stationRepository: StationRepository
) {
    operator fun invoke(city: String, line: String?): Flow<ApiResult<List<Station>>> = flow {
        emit(ApiResult.Loading)
        delay(DELAY_FOR_USE_CASE)
        emit(stationRepository.getStations(city, line))
    }
}