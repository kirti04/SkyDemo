package com.test.skydemo.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.lib_api.base.Result
import com.test.lib_api.domain.usecase.GetNewsListUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val getNewsListUseCase: GetNewsListUseCase
) : ViewModel() {

    val intentions = MutableSharedFlow<NewsIntention>()

    private val _state: MutableStateFlow<NewsState> = MutableStateFlow(NewsState.Idle)
    val state: StateFlow<NewsState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            intentions.asSharedFlow().collect {
                when (it) {
                    is NewsIntention.LoadData -> loadData()
                }
            }
        }
    }

    private fun loadData() {
        _state.value = NewsState.Loading
        viewModelScope.launch {
            val response = getNewsListUseCase.invoke()
            _state.value = when (response) {
                is Result.Success -> NewsState.NewsList(response.value)
                is Result.Error -> NewsState.Error(response.message)
                else -> return@launch
            }
        }
    }
}