package com.shdwraze.metro.domain.usecase.common

import com.shdwraze.metro.data.repository.MetroRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLinesUseCase @Inject constructor(
    private val metroRepository: MetroRepository
) {
    operator fun invoke(): Flow<List<String>> = flow {
        emit(metroRepository.getLines())
    }
}