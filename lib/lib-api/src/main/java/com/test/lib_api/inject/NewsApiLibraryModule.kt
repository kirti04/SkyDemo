package com.test.lib_api.inject

import com.test.lib_api.data.NewsRemoteDataSource
import com.test.lib_api.data.NewsRepoImpl
import com.test.lib_api.domain.repo.NewsRepo
import com.test.lib_api.remote.source.news.NewsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class NewsApiLibraryModule {

    @Binds
    internal abstract fun bindNewsRepo(newsRepo: NewsRepoImpl): NewsRepo

    @Binds
    internal abstract fun bindsNewsRemoteDataSource(
        newsRemoteDataSource: NewsRemoteDataSourceImpl
    ): NewsRemoteDataSource
}