package com.kturker.network

import retrofit2.Response

inline val <T> Response<T>.asRestResult: ServiceResult<T>
    get() {
        return if (isSuccessful) {
            body()?.let { data ->
                ServiceResult.Success(data = data)
            } ?: run {
                createErrorResultWithoutWrapper(response = this)
            }
        } else {
            createErrorResultWithoutWrapper(response = this)
        }
    }


fun <T> createErrorResultWithoutWrapper(response: Response<T>): ServiceResult.Error {
    return ServiceResult.Error(message = response.message())
}

