package com.test.skydemo.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.lib_api.domain.model.Story
import com.test.skydemo.databinding.StoryDetailFragmentBinding
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class NewsDetailFragment : DaggerFragment() {

    private lateinit var binding: StoryDetailFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var newsDetailViewModel: NewsDetailViewModel

    private val newsDetailAdapter by lazy {
        NewsDetailAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsDetailViewModel =
            ViewModelProvider(this, viewModelFactory).get(NewsDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StoryDetailFragmentBinding.inflate(inflater, container, false)
        initUi()
        initIntentions()
        initObserver()

        return binding.root
    }

    private fun initUi() {
        binding.storyDetailRecyclerView.apply {
            adapter = newsDetailAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }
    }

    private fun initIntentions() {
        lifecycleScope.launchWhenStarted {
            newsDetailViewModel.intentions.emit(NewsDetailIntention.LoadData)
        }
    }

    private fun initObserver() {
        lifecycleScope.launchWhenStarted {
            newsDetailViewModel.state.collect {
                when (it) {
                    is NewsDetailState.NewsStoryDetail -> showStoryDetail(it.story)
                    is NewsDetailState.Error -> showError(it.message)
                    else -> return@collect
                }
            }
        }
    }

    private fun showStoryDetail(story: Story) {
        newsDetailAdapter.update(story.contents)
    }

    private fun showError(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}