package com.selincengiz.ghibli.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selincengiz.ghibli.common.DetailState
import com.selincengiz.ghibli.common.Resource
import com.selincengiz.ghibli.common.SearchState
import com.selincengiz.ghibli.data.repo.TvRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val tvRepo: TvRepo

): ViewModel() {

    private var _detailState = MutableLiveData<DetailState>()
    val detailState: LiveData<DetailState>
        get() = _detailState


    fun getTvDetail(id:Int){
        viewModelScope.launch {
            _detailState.value = DetailState.Loading
            val result = tvRepo.getDetailTv(id)
            when (result) {
                is Resource.Success -> {
                    _detailState.value = DetailState.Tv(result.data)
                }

                is Resource.Error -> {
                    _detailState.value = DetailState.Error(result.throwable)
                }
            }
        }
    }

    fun getVideoTv(id:Int){
        viewModelScope.launch {
            _detailState.value = DetailState.Loading
            val result = tvRepo.getVideoTv(id)
            when (result) {
                is Resource.Success -> {
                    _detailState.value = DetailState.Video(result.data)
                }

                is Resource.Error -> {
                    _detailState.value = DetailState.Error(result.throwable)
                }
            }
        }
    }
}