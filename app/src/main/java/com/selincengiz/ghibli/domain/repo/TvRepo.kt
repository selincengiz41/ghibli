package com.selincengiz.ghibli.domain.repo

import com.selincengiz.ghibli.common.Resource
import com.selincengiz.ghibli.domain.entities.Tv
import com.selincengiz.ghibli.domain.entities.TvDetail
import com.selincengiz.ghibli.domain.entities.TvVideo

interface TvRepo {
    suspend fun getDiscoverTv(): Resource<List<Tv>>
    suspend fun getSeekTv(query:String): Resource<List<Tv>>
    suspend fun getPopularTv(): Resource<List<Tv>>
    suspend fun getOnTheAirTv(): Resource<List<Tv>>
    suspend fun getDetailTv(id:Int):Resource<TvDetail>
    suspend fun getVideoTv(id:Int):Resource<List<TvVideo>>
}