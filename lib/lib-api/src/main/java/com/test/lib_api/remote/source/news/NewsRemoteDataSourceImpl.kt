package com.test.lib_api.remote.source.news

import com.test.lib_api.base.Result
import com.test.lib_api.base.mapSuccess
import com.test.lib_api.data.NewsRemoteDataSource
import com.test.lib_api.domain.model.News
import com.test.lib_api.domain.model.Story
import com.test.lib_api.remote.base.BaseRemoteSource
import com.test.lib_api.remote.base.ServiceFactory
import com.test.lib_api.remote.source.detail.StoryMapper
import com.test.lib_api.remote.source.news.stub.FakeApiResponse
import javax.inject.Inject

internal class NewsRemoteDataSourceImpl @Inject constructor(
    private val newsMapper: NewsMapper,
    private val storyMapper: StoryMapper,
    private val serviceFactory: ServiceFactory
) : BaseRemoteSource(), NewsRemoteDataSource {

    override suspend fun getNews(): Result<News> = safeApiCall {
        //serviceFactory.makeService(NewsService::class.java).getNewsList()
        FakeApiResponse.getNewsList()
    }.mapSuccess {
        newsMapper.map(it.value)
    }

    override suspend fun getStoryDetail(id: String): Result<Story> = safeApiCall {
        //serviceFactory.makeService(NewsService::class.java).getStoryDetail()
        FakeApiResponse.getStoryDetail()
    }.mapSuccess {
        storyMapper.map(it.value)
    }
}