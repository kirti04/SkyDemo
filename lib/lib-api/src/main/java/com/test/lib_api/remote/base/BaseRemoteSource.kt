package com.test.lib_api.remote.base

import retrofit2.Response
import com.test.lib_api.base.Result

abstract class BaseRemoteSource {

    protected suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Result<T> {
        return try {
            handleSuccess(apiCall.invoke())
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

    private fun <T> handleSuccess(response: Response<T>): Result<T> {
        val responseBodyValue = response.body()
        return when {
            response.code() in 200..299 && responseBodyValue != null -> Result.Success(
                responseBodyValue
            )
            else -> Result.Error()
        }
    }
}