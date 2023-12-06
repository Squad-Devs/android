package com.shdwraze.metro.data.api.result

sealed class ApiResult<out T> {
    data class Success<out T>(val value: T) : ApiResult<T>()

    data class ServerError(val error: ApiSafeCallerError) : ApiResult<Nothing>()

    object NetworkError : ApiResult<Nothing>()

    object Loading : ApiResult<Nothing>()
}