package com.selincengiz.ghibli.presentation.seek

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selincengiz.ghibli.common.Resource
import com.selincengiz.ghibli.common.SeekState
import com.selincengiz.ghibli.data.repo.TvRepoImpl
import com.selincengiz.ghibli.domain.repo.TvRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeekViewModel @Inject constructor(
    private val tvRepo: TvRepo

):ViewModel() {
    private var _seekState = MutableStateFlow<SeekState>(SeekState.Loading)
    val seekState: StateFlow<SeekState>
        get() = _seekState.asStateFlow()


    fun getSeekTv(query:String) {
        viewModelScope.launch {
            val result = tvRepo.getSeekTv(query)
            when (result) {
                is Resource.Success -> {
                    _seekState.value = SeekState.Seek(result.data)
                }

                is Resource.Error -> {
                    _seekState.value = SeekState.Error(result.throwable)
                }
            }
        }
    }


    fun getDefaultTv() {
        viewModelScope.launch {
            _seekState.value = SeekState.Loading
            val result = tvRepo.getDiscoverTv()
            when (result) {
                is Resource.Success -> {
                    _seekState.value = SeekState.Seek(result.data)
                }

                is Resource.Error -> {
                    _seekState.value = SeekState.Error(result.throwable)
                }
            }
        }
    }


}