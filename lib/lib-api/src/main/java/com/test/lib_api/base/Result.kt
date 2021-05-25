package com.test.lib_api.base

sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Error(val message: String? = null) : Result<Nothing>()
}