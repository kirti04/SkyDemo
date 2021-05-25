package com.test.lib_api.remote.source.detail

import com.test.lib_api.domain.model.Story
import com.test.lib_api.remote.base.Mapper
import java.lang.IllegalArgumentException
import java.util.*
import javax.inject.Inject

internal class StoryMapper @Inject constructor() : Mapper<StoryRemoteResponse, Story> {

    override fun map(input: StoryRemoteResponse) = Story(
        contents = input.contents.map { mapRemoteContentsToDomainContent(it) },
        creationDate = input.creationDate,
        heroImageUrl = input.heroImage.imageUrl,
        id = input.id,
        headline = input.headline
    )

    private fun mapRemoteContentsToDomainContent(
        content: StoryRemoteResponse.ContentsItem
    ): Story.ContentsItem {
        val type = when (content.type.toLowerCase(Locale.UK)) {
            "paragraph" -> Story.ContentsItem.Type.Paragraph(content.text ?: "")
            "image" -> Story.ContentsItem.Type.Image(content.url ?: "")
            else -> throw IllegalArgumentException("Invalid type ${content.type}")
        }
        return Story.ContentsItem(type)
    }
}