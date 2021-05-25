package com.test.lib_api.remote.source.news.stub

import com.test.lib_api.remote.source.detail.StoryRemoteResponse

object StoryRemoteResponseStubFactory {

    fun makeStoryRemote(
        contents: List<StoryRemoteResponse.ContentsItem> = emptyList(),
        heroImageUrl: String = "",
        id: String = "",
        creationDate: String = "",
        headline: String = ""
    ) = StoryRemoteResponse(
        contents = contents,
        heroImage = StoryRemoteResponse.HeroImage(heroImageUrl),
        id = id,
        creationDate = creationDate,
        headline = headline
    )

    fun makeContent(
        text: String? = null,
        type: String = "",
        url: String? = null
    ) = StoryRemoteResponse.ContentsItem(
        text = text,
        type = type,
        url = url
    )
}