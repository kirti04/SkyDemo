package com.test.lib_api.domain.usecase

import com.test.lib_api.domain.repo.NewsRepo
import com.test.lib_api.base.Result
import com.test.lib_api.domain.model.Story
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetStoryDetailUseCase @Inject internal constructor(
    private val newsRepo: NewsRepo
) {
    suspend fun invoke(id: String): Result<Story> = withContext(Dispatchers.IO) {
        newsRepo.getStoryDetail(id)
    }
}