package com.selincengiz.ghibli.ui.seek

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selincengiz.ghibli.common.Resource
import com.selincengiz.ghibli.common.SearchState
import com.selincengiz.ghibli.common.SeekState
import com.selincengiz.ghibli.data.repo.TvRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeekViewModel @Inject constructor(
    private val tvRepo: TvRepo

):ViewModel() {
    private var _seekState = MutableLiveData<SeekState>()
    val seekState: LiveData<SeekState>
        get() = _seekState


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