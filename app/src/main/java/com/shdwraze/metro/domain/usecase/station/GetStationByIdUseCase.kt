package com.shdwraze.metro.domain.usecase.station

import com.shdwraze.metro.common.Constants
import com.shdwraze.metro.data.api.result.ApiResult
import com.shdwraze.metro.data.model.Station
import com.shdwraze.metro.data.repository.StationRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetStationByIdUseCase @Inject constructor(
    private val stationRepository: StationRepository
) {
    operator fun invoke(id: String): Flow<ApiResult<Station>> = flow {
        emit(ApiResult.Loading)
        delay(Constants.DELAY_FOR_USE_CASE)
        emit(stationRepository.getStationById(id))
    }
}