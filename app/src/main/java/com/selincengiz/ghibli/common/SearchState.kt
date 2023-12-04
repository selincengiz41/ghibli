package com.selincengiz.ghibli.common

import com.selincengiz.ghibli.domain.entities.Tv


sealed interface SearchState {
    object Loading : SearchState
    data class Discover(val tv: List<Tv>) : SearchState
    data class Popular(val tv: List<Tv>) : SearchState
    data class Error(val throwable: Throwable) : SearchState
    data class OnTheAir(val tv: List<Tv>) : SearchState


}