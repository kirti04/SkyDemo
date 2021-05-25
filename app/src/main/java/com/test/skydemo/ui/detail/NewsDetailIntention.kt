package com.test.skydemo.ui.detail

sealed class NewsDetailIntention {
    object LoadData : NewsDetailIntention()
}