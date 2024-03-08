package com.shdwraze.metro.domain.usecase.metropolitan

import com.shdwraze.metro.common.Constants
import com.shdwraze.metro.data.api.result.ApiResult
import com.shdwraze.metro.data.model.Metropolitan
import com.shdwraze.metro.data.repository.MetroRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMetropolitanUseCase @Inject constructor(
    private val metroRepository: MetroRepository
) {

    operator fun invoke(city: String): Flow<ApiResult<Metropolitan>> = flow {
        emit(ApiResult.Loading)
        delay(Constants.DELAY_FOR_USE_CASE)
        emit(metroRepository.getMetropolitan(city))
    }
}