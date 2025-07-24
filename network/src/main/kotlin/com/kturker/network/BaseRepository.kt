package com.kturker.network

import com.kturker.contract.RestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

open class BaseRepository {

    suspend inline fun <reified T : Any> request(
        crossinline call: suspend () -> Response<T>
    ): RestResult<T> = withContext(Dispatchers.IO) {
        try {
            call.invoke().asRestResult
        } catch (exception: Exception) {
            RestResult.Error(exception.message.orEmpty())
        }
    }
}