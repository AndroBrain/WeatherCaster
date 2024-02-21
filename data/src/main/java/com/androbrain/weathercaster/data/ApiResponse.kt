package com.androbrain.weathercaster.data

import com.androbrain.weathercaster.domain.core.ResultErrorType
import okhttp3.Headers
import retrofit2.Response

sealed class ApiResponse<out T> {
    data class Ok<out T>(
        val headers: Headers,
        val body: T,
    ) : ApiResponse<T>()

    data class Error(
        val exception: ApiException,
    ) : ApiResponse<Nothing>() {
        fun toResultErrorType() =
            when (exception.httpCode) {
                400 -> ResultErrorType.WRONG
                404 -> ResultErrorType.NOT_FOUND
                429 -> ResultErrorType.TOO_MANY_REQUESTS
                500 -> ResultErrorType.SERVER
                else -> ResultErrorType.UNKNOWN
            }
    }

    data class NetworkError(
        val throwable: Throwable,
    ) : ApiResponse<Nothing>()
}

class ApiException(
    val httpCode: Int,
    val errorBody: Response<*>?,
) : Exception()
