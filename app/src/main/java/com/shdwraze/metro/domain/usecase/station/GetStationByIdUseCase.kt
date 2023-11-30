package com.shdwraze.metro.domain.usecase.station

import com.shdwraze.metro.data.model.Station
import com.shdwraze.metro.data.repository.StationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetStationByIdUseCase @Inject constructor(
    private val stationRepository: StationRepository
) {
    operator fun invoke(id: String): Flow<Station> = flow {
        emit(stationRepository.getStationById(id))
    }
}