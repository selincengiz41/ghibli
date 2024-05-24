package com.selincengiz.ghibli.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selincengiz.ghibli.common.DetailState
import com.selincengiz.ghibli.common.Resource
import com.selincengiz.ghibli.data.entities.FavoriteTv
import com.selincengiz.ghibli.data.repo.FavoriteRepoImpl
import com.selincengiz.ghibli.data.repo.TvRepoImpl
import com.selincengiz.ghibli.domain.repo.FavoriteRepo
import com.selincengiz.ghibli.domain.repo.TvRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val tvRepo: TvRepo,
    private val favoriteRepo: FavoriteRepo,


    ) : ViewModel() {

    private var _detailState =
        MutableStateFlow<DetailState>(DetailState.Loading)
    val detailState: StateFlow<DetailState>
        get() = _detailState.asStateFlow()


    fun getTvDetail(id: Int) {
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

    fun isFavorite(id: Int) {
        viewModelScope.launch {
            _detailState.value = DetailState.Loading
            val result = favoriteRepo.getFavorites()
            when (result) {
                is Resource.Success -> {
                 val res=result.data.find {it.id==id
                    }
                    if(res==null){
                        _detailState.value = DetailState.IsFavorite(false)
                    }
                    else{
                        _detailState.value = DetailState.IsFavorite(true)
                    }

                }

                is Resource.Error -> {
                    _detailState.value = DetailState.Error(result.throwable)
                }
            }
        }
    }

    fun addFavorite(tv: FavoriteTv){
        viewModelScope.launch {
        favoriteRepo.addFavorites(tv)

        }
    }
    fun deleteFavorite(tv: FavoriteTv){
        viewModelScope.launch {
        favoriteRepo.deleteFavorites(tv)

        }
    }

    fun getVideoTv(id: Int) {
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