package com.selincengiz.ghibli.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selincengiz.ghibli.common.SearchState
import com.selincengiz.ghibli.common.Resource
import com.selincengiz.ghibli.data.repo.TvRepoImpl
import com.selincengiz.ghibli.domain.repo.TvRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val tvRepo: TvRepo

) : ViewModel() {
    private var _searchState = MutableStateFlow<SearchState>(SearchState.Loading)
    val searchState: StateFlow<SearchState>
        get() = _searchState.asStateFlow()

    fun getDiscoverTv() {
        viewModelScope.launch {
            _searchState.value = SearchState.Loading
            val result = tvRepo.getDiscoverTv()
            when (result) {
                is Resource.Success -> {
                    _searchState.value = SearchState.Discover(result.data)
                }

                is Resource.Error -> {
                    _searchState.value = SearchState.Error(result.throwable)
                }
            }
        }
    }

    fun getPopularTv() {
        viewModelScope.launch {
            _searchState.value = SearchState.Loading
            val result = tvRepo.getPopularTv()
            when (result) {
                is Resource.Success -> {
                    _searchState.value = SearchState.Popular(result.data)
                }

                is Resource.Error -> {
                    _searchState.value = SearchState.Error(result.throwable)
                }
            }
        }
    }

    fun getOnTheAirTv() {
        viewModelScope.launch {
            _searchState.value = SearchState.Loading
            val result = tvRepo.getOnTheAirTv()
            when (result) {
                is Resource.Success -> {
                    _searchState.value = SearchState.OnTheAir(result.data)
                }

                is Resource.Error -> {
                    _searchState.value = SearchState.Error(result.throwable)
                }
            }
        }
    }

}