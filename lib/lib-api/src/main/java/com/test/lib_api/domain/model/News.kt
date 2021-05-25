package com.test.lib_api.domain.model

import org.joda.time.DateTime

data class News(
    val items: List<Item>,
    val title: String
) {

    data class Item(
        val teaserText: String,
        val modifiedDate: String,
        val id: String,
        val type: Type,
        val creationDate: DateTime,
        val headline: String,
        val teaserImageUrl: TeaserImageUrl,
        val webLinkUrl: String?
    ) {

        enum class Type {
            STORY,
            WEB_LINK,
            ADVERT
        }
    }

    data class TeaserImageUrl(
        val templated: Boolean,
        val href: String,
        val type: String
    )
}


