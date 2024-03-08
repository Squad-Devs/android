package com.shdwraze.metro.domain.usecase.path

import com.shdwraze.metro.common.Constants
import com.shdwraze.metro.data.api.result.ApiResult
import com.shdwraze.metro.data.model.ShortestPath
import com.shdwraze.metro.data.repository.MetroRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetShortestPathUseCase @Inject constructor(
    private val metroRepository: MetroRepository
) {

    operator fun invoke(from: Int, to: Int): Flow<ApiResult<ShortestPath>> = flow {
        emit(ApiResult.Loading)
        delay(Constants.DELAY_FOR_USE_CASE)
        emit(metroRepository.getShortestPath(from, to))
    }
}