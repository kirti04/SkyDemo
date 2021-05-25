package com.test.skydemo.ui.news

import com.test.lib_api.domain.model.News
import org.joda.time.DateTime

internal object NewsStubFactory {

    fun makeNews(
        title: String = "",
        items: List<News.Item> = emptyList()
    ) = News(items, title)

    fun makeNewsItem(
        teaserText: String = "",
        modifiedDate: String = "",
        id: String = "",
        type: News.Item.Type = News.Item.Type.STORY,
        creationDate: DateTime = DateTime.now(),
        headline: String = "",
        teaserImageUrl: News.TeaserImageUrl = makeTeaserUrl(),
        webLinkUrl: String? = null
    ) = News.Item(
        teaserText,
        modifiedDate,
        id,
        type,
        creationDate,
        headline,
        teaserImageUrl,
        webLinkUrl
    )

    private fun makeTeaserUrl(
        templated: Boolean = false,
        href: String = "",
        type: String = ""
    ) = News.TeaserImageUrl(templated, href, type)
}