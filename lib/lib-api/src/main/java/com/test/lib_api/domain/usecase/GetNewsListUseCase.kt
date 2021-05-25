package com.test.lib_api.domain.usecase

import com.test.lib_api.domain.repo.NewsRepo
import com.test.lib_api.base.Result
import com.test.lib_api.domain.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetNewsListUseCase @Inject internal constructor(
    private val newsRepo: NewsRepo
) {
    suspend fun invoke(): Result<News> = withContext(Dispatchers.IO) {
        newsRepo.getNews()
    }
}