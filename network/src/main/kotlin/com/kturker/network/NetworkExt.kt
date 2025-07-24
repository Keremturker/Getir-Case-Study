package com.kturker.network

import com.kturker.contract.RestResult
import retrofit2.Response

inline val <T> Response<T>.asRestResult: RestResult<T>
    get() {
        return if (isSuccessful) {
            body()?.let { data ->
                RestResult.Success(data)
            } ?: run {
                createErrorResultWithoutWrapper(this)
            }
        } else {
            createErrorResultWithoutWrapper(this)
        }
    }


fun <T> createErrorResultWithoutWrapper(response: Response<T>): RestResult.Error {
    return RestResult.Error(error = response.message())
}

