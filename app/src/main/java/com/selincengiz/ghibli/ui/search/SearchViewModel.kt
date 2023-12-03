package com.selincengiz.ghibli.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selincengiz.ghibli.common.SearchState
import com.selincengiz.ghibli.common.Resource
import com.selincengiz.ghibli.data.repo.TvRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val tvRepo: TvRepo

) : ViewModel() {
    private var _searchState = MutableLiveData<SearchState>()
    val searchState: LiveData<SearchState>
        get() = _searchState

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