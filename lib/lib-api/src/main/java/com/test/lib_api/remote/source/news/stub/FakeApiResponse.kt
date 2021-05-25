package com.test.lib_api.remote.source.news.stub

import com.test.lib_api.remote.source.detail.StoryRemoteResponse
import com.test.lib_api.remote.source.news.NewsRemoteResponse
import com.test.lib_api.remote.source.news.stub.NewsRemoteResponseStubFactory.makeNewsRemote
import retrofit2.Response

object FakeApiResponse {

    fun getNewsList(): Response<NewsRemoteResponse> {

        val storyList = List(size = 5) {
            NewsRemoteResponseStubFactory.makeDataItem(
                teaserText = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
                id = it.toString(),
                headline = "Story Headline",
                creationDate = "2020-11-18T00:00:00Z",
                type = "story",
                teaserImage = NewsRemoteResponseStubFactory.makeTeaserImage(href = "https://cdn2.thecatapi.com/images/ozEvzdVM-.jpg")
            )
        }

        val webLinkList = List(size = 3) {
            NewsRemoteResponseStubFactory.makeDataItem(
                teaserText = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
                id = "4",
                headline = "Weblink headline",
                weblinkUrl = "https://cdn2.thecatapi.com/images/ozEvzdVM-.jpg",
                creationDate = "2020-11-18T00:00:00Z",
                type = "weblink",
                teaserImage = NewsRemoteResponseStubFactory.makeTeaserImage(href = "https://cdn2.thecatapi.com/images/ozEvzdVM-.jpg")
            )
        }
        val data = storyList + webLinkList
        return Response.success(makeNewsRemote(data = data, title = "Sky Cat News"))
    }

    fun getStoryDetail(): Response<StoryRemoteResponse> {
        StoryRemoteResponseStubFactory.run {
            val paragraphComponents = List(size = 3) {
                makeContent(
                    type = "paragraph",
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                )
            }

            val imageComponents = List(size = 2) {
                makeContent(
                    type = "image",
                    url = "https://cdn2.thecatapi.com/images/ozEvzdVM-.jpg"
                )
            }
            return Response.success(
                makeStoryRemote(
                    contents = paragraphComponents + imageComponents,
                    heroImageUrl = "https://cdn2.thecatapi.com/images/ozEvzdVM-.jpg",
                    id = "1",
                    creationDate = "2020-11-18T00:00:00Z",
                    headline = "Cat story headline"
                )
            )
        }
    }
}