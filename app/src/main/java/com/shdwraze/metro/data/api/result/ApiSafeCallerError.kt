package com.shdwraze.metro.data.api.result

enum class ApiSafeCallerError(val code: Int, val message: String) {
    UNKNOWN_EXCEPTION(
        code = 0,
        message = "Unknown exception"
    ),
    UNKNOWN_HTTP_EXCEPTION(
        code = 1,
        message = "Unknown http exception"
    ),
    NO_INTERNET_CONNECTION(
        code = 2,
        message = "No internet connection"
    )
}