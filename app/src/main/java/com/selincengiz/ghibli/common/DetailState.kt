package com.selincengiz.ghibli.common

import com.selincengiz.ghibli.data.entities.TvDetailRespond
import com.selincengiz.ghibli.domain.entities.Tv
import com.selincengiz.ghibli.domain.entities.TvDetail

sealed interface DetailState{
    object Loading : DetailState
    data class Tv(val tv: TvDetail) : DetailState
    data class Error(val throwable: Throwable) : DetailState

}