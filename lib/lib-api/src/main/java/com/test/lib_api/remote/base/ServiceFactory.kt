package com.test.lib_api.remote.base

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ServiceFactory @Inject constructor() {

    companion object {
        private const val BASE_URL: String = "https://base-url"
    }

    inline fun <reified T> makeService(service: Class<T>): T =
        makeRetrofit().create(service)

    private fun makeRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(makeOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    private fun makeOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(makeHttpLoggingInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(25, TimeUnit.SECONDS)
        .build()

    private fun makeHttpLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}