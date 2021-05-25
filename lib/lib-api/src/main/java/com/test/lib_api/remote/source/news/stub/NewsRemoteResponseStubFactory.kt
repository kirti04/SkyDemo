package com.test.lib_api.remote.source.news.stub

import com.test.lib_api.remote.source.news.NewsRemoteResponse

object NewsRemoteResponseStubFactory {

    fun makeNewsRemote(
        data: List<NewsRemoteResponse.DataItem> = emptyList(),
        title: String = ""
    ): NewsRemoteResponse = NewsRemoteResponse(data, title)

    fun makeDataItem(
        teaserText: String = "",
        modifiedDate: String = "",
        id: String = "",
        type: String = "",
        creationDate: String = "",
        headline: String = "",
        teaserImage: NewsRemoteResponse.TeaserImage = makeTeaserImage(),
        weblinkUrl: String? = null
    ): NewsRemoteResponse.DataItem = NewsRemoteResponse.DataItem(
        teaserText = teaserText,
        modifiedDate = modifiedDate,
        id = id,
        type = type,
        creationDate = creationDate,
        headline = headline,
        teaserImage = teaserImage,
        weblinkUrl = weblinkUrl
    )

    fun makeTeaserImage(
        templated: Boolean = false,
        href: String = "",
        type: String = "",
        accessibilityText: String = ""
    ): NewsRemoteResponse.TeaserImage {
        val links = NewsRemoteResponse.Links(NewsRemoteResponse.Url(templated, href, type))
        return NewsRemoteResponse.TeaserImage(links, accessibilityText)
    }
}