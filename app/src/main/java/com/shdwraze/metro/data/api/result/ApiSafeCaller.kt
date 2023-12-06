package com.shdwraze.metro.data.api.result

import com.shdwraze.metro.data.api.result.ApiSafeCallerError.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

object ApiSafeCaller {

    suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): ApiResult<T> {
        return withContext(dispatcher) {
            try {
                ApiResult.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> ApiResult.NetworkError
                    is HttpException -> {
                        ApiResult.ServerError(UNKNOWN_HTTP_EXCEPTION)
                    }
                    else -> {
                        ApiResult.ServerError(UNKNOWN_EXCEPTION)
                    }
                }
            }
        }
    }

}