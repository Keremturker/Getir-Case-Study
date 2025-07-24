package com.kturker.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

open class BaseRepository {

    suspend inline fun <reified T : Any> request(
        crossinline call: suspend () -> Response<T>
    ): ServiceResult<T> = withContext(context = Dispatchers.IO) {
        try {
            call.invoke().asRestResult
        } catch (exception: Exception) {
            ServiceResult.Error(message = exception.message.orEmpty())
        }
    }
}