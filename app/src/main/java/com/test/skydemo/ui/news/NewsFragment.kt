package com.test.skydemo.ui.news

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.lib_api.domain.model.News
import com.test.skydemo.R
import com.test.skydemo.databinding.NewsFragmentBinding
import com.test.skydemo.ui.extension.formattedString
import com.test.skydemo.ui.extension.loadImage
import com.test.skydemo.ui.extension.showExternalWebBrowser
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class NewsFragment : DaggerFragment(), OnItemClickListener {

    private lateinit var binding: NewsFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var newsViewModel: NewsViewModel

    private val feedAdapter by lazy {
        NewsAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsViewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewsFragmentBinding.inflate(inflater, container, false)
        initUi()
        initIntentions()
        initObserver()

        return binding.root
    }

    private fun initUi() {
        binding.newsRecyclerView.apply {
            adapter = feedAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }
    }

    private fun initIntentions() {
        lifecycleScope.launchWhenStarted {
            newsViewModel.intentions.emit(NewsIntention.LoadData)
        }
    }

    private fun initObserver() {
        lifecycleScope.launchWhenStarted {
            newsViewModel.state.collect {
                when (it) {
                    is NewsState.Loading -> showLoading()
                    is NewsState.NewsList -> showNewsList(it.news)
                    is NewsState.Error -> showError(it.message)
                    else -> return@collect
                }
            }
        }
    }

    private fun showLoading() {
        Toast.makeText(requireContext(), "Loading data..", Toast.LENGTH_SHORT).show()
    }

    private fun showNewsList(news: News) {
        binding.newsToolbarTitle.text = news.title
        setTopNewsInfo(news)
        feedAdapter.update(news.items)
    }

    private fun setTopNewsInfo(news: News) {
        val topNews = news.items.firstOrNull() ?: return
        binding.newsTopHeadlineImage.loadImage(topNews.teaserImageUrl.href)
        binding.newsTopHeadlineInfo.apply {
            topNewsHeadline.text = topNews.headline
            topNewsTeaserText.text = topNews.teaserText
            topNewsCreationDate.text = topNews.creationDate.formattedString
        }
    }

    private fun showError(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClicked(newsItem: News.Item) {
        when (newsItem.type) {
            News.Item.Type.STORY -> showStoryDetail(newsItem)
            News.Item.Type.WEB_LINK -> openWebLink(newsItem)
            else -> return
        }
    }

    private fun showStoryDetail(newsItem: News.Item) {
        //val args = FeedDetailFragment.Args(sale.title)
        findNavController().navigate(R.id.newsDetailFragment)
    }

    private fun openWebLink(newsItem: News.Item) {
        newsItem.webLinkUrl?.let { requireContext().showExternalWebBrowser(it) }
    }
}