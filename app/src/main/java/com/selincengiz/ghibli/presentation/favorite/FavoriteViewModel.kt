package com.selincengiz.ghibli.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selincengiz.ghibli.common.FavoriteState
import com.selincengiz.ghibli.common.Resource
import com.selincengiz.ghibli.data.repo.FavoriteRepo
import com.selincengiz.ghibli.domain.mapper.mapToTv
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteRepo: FavoriteRepo):ViewModel() {
    private var _favoriteState = MutableStateFlow<FavoriteState>(FavoriteState.Loading)
    val favoriteState: StateFlow<FavoriteState>
        get() = _favoriteState.asStateFlow()

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