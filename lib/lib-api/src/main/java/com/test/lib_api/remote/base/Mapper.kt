package com.test.lib_api.remote.base

internal interface Mapper<I, out O> {
    fun map(input: I): O
}