package com.test.lib_api.domain.model

data class Story(
    val contents: List<ContentsItem>,
    val heroImageUrl: String,
    val id: String,
    val creationDate: String,
    val headline: String
) {

    data class ContentsItem(val type: Type) {

        sealed class Type {
            data class Paragraph(val text: String) : Type()
            data class Image(val url: String) : Type()
        }
    }
}