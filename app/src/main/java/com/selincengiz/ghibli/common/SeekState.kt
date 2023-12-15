package com.selincengiz.ghibli.common

import com.selincengiz.ghibli.domain.entities.Tv

sealed interface SeekState{
    object Loading : SeekState
    data class Seek(val tv: List<Tv>) : SeekState
    data class Error(val throwable: Throwable) : SeekState
}