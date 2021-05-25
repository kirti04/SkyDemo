package com.test.lib_api.base

inline fun <T : Any?> Result<T>.abortOnError(func: (Result.Error) -> Unit): T = when (this) {
    is Result.Success -> value
    is Result.Error -> func(this) as Nothing
}

inline fun <T : Any?, M : Any?> Result<T>.mapSuccess(mapper: (Result.Success<T>) -> M): Result<M> =
    when (this) {
        is Result.Success -> Result.Success(mapper(this))
        is Result.Error -> this
    }