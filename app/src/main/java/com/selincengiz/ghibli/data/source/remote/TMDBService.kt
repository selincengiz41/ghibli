package com.selincengiz.ghibli.data.source.remote


import com.selincengiz.ghibli.common.Constants.DETAIL_TV
import com.selincengiz.ghibli.common.Constants.DISCOVER_TV
import com.selincengiz.ghibli.common.Constants.ON_THE_AIR_TV
import com.selincengiz.ghibli.common.Constants.POPULAR_TV
import com.selincengiz.ghibli.common.Constants.VIDEO_TV
import com.selincengiz.ghibli.data.entities.TvDetailRespond
import com.selincengiz.ghibli.data.entities.TvRespond
import com.selincengiz.ghibli.data.entities.TvVideoResponse

import retrofit2.http.GET
import retrofit2.http.Path

interface TMDBService {

    @GET(DISCOVER_TV)
    suspend fun getDiscoverTv(): TvRespond

    @GET(POPULAR_TV)
    suspend fun getPopularTv(): TvRespond

    @GET(ON_THE_AIR_TV)
    suspend fun getOnTheAirTv(): TvRespond

    @GET(DETAIL_TV)
    suspend fun getDetailTv(@Path("series_id") id :Int): TvDetailRespond

    @GET(VIDEO_TV)
    suspend fun getVideoTv(@Path("series_id") id :Int): TvVideoResponse
}