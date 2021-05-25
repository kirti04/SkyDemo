package com.test.skydemo.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.lib_api.base.Result
import com.test.lib_api.domain.usecase.GetStoryDetailUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsDetailViewModel @Inject constructor(
    private val getStoryDetailUseCase: GetStoryDetailUseCase
) : ViewModel() {

    val intentions = MutableSharedFlow<NewsDetailIntention>()

    private val _state: MutableStateFlow<NewsDetailState> = MutableStateFlow(NewsDetailState.Idle)
    val state: StateFlow<NewsDetailState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            intentions.asSharedFlow().collect {
                when (it) {
                    is NewsDetailIntention.LoadData -> loadData()
                }
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            val response = getStoryDetailUseCase.invoke(id = "1")
            _state.value = when (response) {
                is Result.Success -> NewsDetailState.NewsStoryDetail(response.value)
                is Result.Error -> NewsDetailState.Error(response.message)
                else -> return@launch
            }
        }
    }
}