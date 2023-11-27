package com.shdwraze.metro.domain.usecase.station

import com.shdwraze.metro.data.model.Station
import com.shdwraze.metro.data.repository.StationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetStationsUseCase @Inject constructor(
    private val stationRepository: StationRepository
) {
    operator fun invoke(city: String, line: String?): Flow<List<Station>> = flow {
        emit(stationRepository.getStations(city, line))
    }
}