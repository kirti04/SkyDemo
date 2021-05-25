package com.test.lib_api.data

import com.test.lib_api.domain.repo.NewsRepo
import com.test.lib_api.base.Result
import com.test.lib_api.domain.model.News
import com.test.lib_api.domain.model.Story
import javax.inject.Inject

internal class NewsRepoImpl @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource
) : NewsRepo {

    override suspend fun getNews(): Result<News> = newsRemoteDataSource.getNews()

    override suspend fun getStoryDetail(id: String): Result<Story> =
        newsRemoteDataSource.getStoryDetail(id)
}