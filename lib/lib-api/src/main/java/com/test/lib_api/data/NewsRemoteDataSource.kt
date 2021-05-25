package com.test.lib_api.data

import com.test.lib_api.base.Result
import com.test.lib_api.domain.model.News
import com.test.lib_api.domain.model.Story

internal interface NewsRemoteDataSource {
    suspend fun getNews(): Result<News>
    suspend fun getStoryDetail(id: String): Result<Story>
}