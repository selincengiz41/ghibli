package com.selincengiz.ghibli.common

import com.selincengiz.ghibli.data.entities.TvDetailRespond
import com.selincengiz.ghibli.domain.entities.Tv
import com.selincengiz.ghibli.domain.entities.TvDetail
import com.selincengiz.ghibli.domain.entities.TvVideo

sealed interface DetailState{
    object Loading : DetailState
    data class Tv(val tv: TvDetail) : DetailState
    data class Video(val videos :List<TvVideo>):DetailState
    data class Error(val throwable: Throwable) : DetailState

}