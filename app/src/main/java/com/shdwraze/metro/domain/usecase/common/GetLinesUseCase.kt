package com.shdwraze.metro.domain.usecase.common

import com.shdwraze.metro.data.repository.CommonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLinesUseCase @Inject constructor(
    private val commonRepository: CommonRepository
) {
    operator fun invoke(): Flow<List<String>> = flow {
        emit(commonRepository.getLines())
    }
}