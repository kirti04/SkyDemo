package com.test.lib_api.remote.source.news

import com.google.gson.annotations.SerializedName

data class NewsRemoteResponse(
    @SerializedName("data")
    val data: List<DataItem>?,
    @SerializedName("title")
    val title: String = ""
) {

    data class DataItem(
        @SerializedName("teaserText")
        val teaserText: String = "",
        @SerializedName("modifiedDate")
        val modifiedDate: String = "",
        @SerializedName("id")
        val id: String = "",
        @SerializedName("type")
        val type: String = "",
        @SerializedName("creationDate")
        val creationDate: String = "",
        @SerializedName("headline")
        val headline: String = "",
        @SerializedName("teaserImage")
        val teaserImage: TeaserImage,
        @SerializedName("weblinkUrl")
        val weblinkUrl: String?
    )

    data class TeaserImage(
        @SerializedName("_links")
        val Links: Links,
        @SerializedName("accessibilityText")
        val accessibilityText: String
    )

    data class Links(
        @SerializedName("url")
        val url: Url
    )

    data class Url(
        @SerializedName("templated")
        val templated: Boolean = false,
        @SerializedName("href")
        val href: String = "",
        @SerializedName("type")
        val type: String = ""
    )
}


