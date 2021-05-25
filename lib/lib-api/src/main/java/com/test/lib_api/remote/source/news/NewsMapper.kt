package com.test.lib_api.remote.source.news

import com.test.lib_api.domain.model.News
import com.test.lib_api.remote.base.Mapper
import java.lang.IllegalArgumentException
import java.util.*
import javax.inject.Inject

internal class NewsMapper @Inject constructor() : Mapper<NewsRemoteResponse, News> {

    override fun map(input: NewsRemoteResponse) = News(
        title = input.title,
        items = input.data?.map { mapDataToItems(it) } ?: emptyList()
    )

    private fun mapDataToItems(
        data: NewsRemoteResponse.DataItem
    ) = News.Item(
        teaserText = data.teaserText,
        modifiedDate = data.modifiedDate,
        id = data.id,
        type = mapTypeToDomainType(data.type),
        creationDate = data.creationDate.date,
        headline = data.headline,
        teaserImageUrl = mapRemoteTeaserImageToDomainTeaserImageUrl(data.teaserImage),
        webLinkUrl = data.weblinkUrl
    )

    private fun mapTypeToDomainType(type: String): News.Item.Type =
        when (type.toLowerCase(Locale.UK)) {
            "story" -> News.Item.Type.STORY
            "weblink" -> News.Item.Type.WEB_LINK
            "advert" -> News.Item.Type.ADVERT
            else -> throw IllegalArgumentException("Invalid type: $type")

        }

    private fun mapRemoteTeaserImageToDomainTeaserImageUrl(
        teaserImage: NewsRemoteResponse.TeaserImage
    ) = News.TeaserImageUrl(
        templated = teaserImage.Links.url.templated,
        href = teaserImage.Links.url.href,
        type = teaserImage.Links.url.type
    )
}