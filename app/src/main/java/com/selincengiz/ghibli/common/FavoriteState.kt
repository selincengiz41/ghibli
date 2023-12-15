package com.selincengiz.ghibli.common

import com.selincengiz.ghibli.data.entities.FavoriteTv
import com.selincengiz.ghibli.domain.entities.Tv

sealed interface FavoriteState{
    object Loading : FavoriteState
    data class Favorite(val tv: List<Tv>) : FavoriteState
    data class Error(val throwable: Throwable) : FavoriteState

}