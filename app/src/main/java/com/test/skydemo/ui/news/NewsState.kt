package com.test.skydemo.ui.news

import com.test.lib_api.domain.model.News

sealed class NewsState {
    object Idle : NewsState()
    object Loading : NewsState()
    data class NewsList(val news: News) : NewsState()
    data class Error(val message: String? = null) : NewsState()
}