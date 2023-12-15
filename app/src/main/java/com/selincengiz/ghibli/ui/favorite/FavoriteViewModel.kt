package com.selincengiz.ghibli.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selincengiz.ghibli.common.FavoriteState
import com.selincengiz.ghibli.common.Resource
import com.selincengiz.ghibli.common.SearchState
import com.selincengiz.ghibli.data.repo.FavoriteRepo
import com.selincengiz.ghibli.domain.mapper.mapToTv
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteRepo: FavoriteRepo):ViewModel() {
    private var _favoriteState = MutableLiveData<FavoriteState>()
    val favoriteState: LiveData<FavoriteState>
        get() = _favoriteState

    fun getFavorites() {
        viewModelScope.launch {
            _favoriteState.value = FavoriteState.Loading
            val result = favoriteRepo.getFavorites()
            when (result) {
                is Resource.Success -> {
                    _favoriteState.value = FavoriteState.Favorite(result.data.map {
                        it.mapToTv()
                    })
                }

                is Resource.Error -> {
                    _favoriteState.value = FavoriteState.Error(result.throwable)
                }
            }
        }
    }
}