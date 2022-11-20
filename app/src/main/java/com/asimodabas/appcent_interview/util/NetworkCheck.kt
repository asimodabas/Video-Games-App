package com.asimodabas.appcent_interview.util

sealed class NetworkCheck<T>(
    val data: T? = null,
    val message: String? = null,
    val networkError: Boolean? = false
) {
    class Success<T>(data: T) : NetworkCheck<T>(data)
    class Error<T>(message: String?, networkError: Boolean) : NetworkCheck<T>(
        data = null,
        message = message,
        networkError = networkError
    )
}
