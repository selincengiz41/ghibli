package com.selincengiz.ghibli.data.api


import com.selincengiz.ghibli.common.Constants.DISCOVER_TV
import com.selincengiz.ghibli.common.Constants.ON_THE_AIR_TV
import com.selincengiz.ghibli.common.Constants.POPULAR_TV
import com.selincengiz.ghibli.data.entities.TvRespond

import retrofit2.http.GET

interface TMDBService {

    @GET(DISCOVER_TV)
    suspend fun getDiscoverTv(): TvRespond

    @GET(POPULAR_TV)
    suspend fun getPopularTv(): TvRespond

    @GET(ON_THE_AIR_TV)
    suspend fun getOnTheAirTv(): TvRespond
}