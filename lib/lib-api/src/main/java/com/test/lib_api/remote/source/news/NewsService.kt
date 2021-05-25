package com.test.lib_api.remote.source.news

import com.test.lib_api.remote.source.detail.StoryRemoteResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface NewsService {

    @GET("/news-list")
    suspend fun getNewsList(): Response<NewsRemoteResponse>

    @GET("/story/{id}")
    suspend fun getStoryDetail(
        @Path("id") id: String
    ): Response<StoryRemoteResponse>
}