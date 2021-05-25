package com.test.skydemo.ui.detail

import com.test.lib_api.domain.model.Story

sealed class NewsDetailState {
    object Idle : NewsDetailState()
    data class NewsStoryDetail(val story: Story) : NewsDetailState()
    data class Error(val message: String?) : NewsDetailState()
}