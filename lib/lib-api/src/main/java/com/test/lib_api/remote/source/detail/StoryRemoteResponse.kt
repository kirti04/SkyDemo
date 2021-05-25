package com.test.lib_api.remote.source.detail

import com.google.gson.annotations.SerializedName

data class StoryRemoteResponse(
    @SerializedName("contents")
    val contents: List<ContentsItem>,
    @SerializedName("heroImage")
    val heroImage: HeroImage,
    @SerializedName("id")
    val id: String,
    @SerializedName("creationDate")
    val creationDate: String,
    @SerializedName("headline")
    val headline: String
) {

    data class ContentsItem(
        @SerializedName("text")
        val text: String?,
        @SerializedName("type")
        val type: String,
        @SerializedName("url")
        val url: String?
    )

    data class HeroImage(
        @SerializedName("imageUrl")
        val imageUrl: String
    )
}


