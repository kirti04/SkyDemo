package com.test.skydemo.ui.news

sealed class NewsIntention {
    object LoadData : NewsIntention()
}