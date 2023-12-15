package com.selincengiz.ghibli.data.repo


import com.selincengiz.ghibli.common.Resource
import com.selincengiz.ghibli.data.source.remote.TMDBService
import java.lang.Exception
import com.selincengiz.ghibli.domain.entities.Tv
import com.selincengiz.ghibli.domain.entities.TvDetail
import com.selincengiz.ghibli.domain.entities.TvVideo
import com.selincengiz.ghibli.domain.mapper.mapToTv
import com.selincengiz.ghibli.domain.mapper.mapToTvDetail
import com.selincengiz.ghibli.domain.mapper.mapToTvVideo

class TvRepo(
    private val TMDBService: TMDBService,

    ) {


    suspend fun getDiscoverTv(): Resource<List<Tv>> {

        return try {
            Resource.Success(TMDBService.getDiscoverTv().results!!.map { result ->
                result.mapToTv()

            })
        } catch (e: Exception) {

            Resource.Error(e)
        }
    }

    suspend fun getSeekTv(query:String): Resource<List<Tv>> {

        return try {
            Resource.Success(TMDBService.getSeekTv(query).results!!.map { result ->
                result.mapToTv()

            })
        } catch (e: Exception) {

            Resource.Error(e)
        }
    }

    suspend fun getPopularTv(): Resource<List<Tv>> {

        return try {
            Resource.Success(TMDBService.getPopularTv().results!!.map { result ->
                result.mapToTv()

            })
        } catch (e: Exception) {

            Resource.Error(e)
        }
    }

    suspend fun getOnTheAirTv(): Resource<List<Tv>> {

        return try {
            Resource.Success(TMDBService.getOnTheAirTv().results!!.map { result ->
                result.mapToTv()

            })
        } catch (e: Exception) {

            Resource.Error(e)
        }
    }

    suspend fun getDetailTv(id:Int):Resource<TvDetail>{
        return try {
            Resource.Success(TMDBService.getDetailTv(id).mapToTvDetail())
        } catch (e: Exception) {

            Resource.Error(e)
        }
    }

    suspend fun getVideoTv(id:Int):Resource<List<TvVideo>>{
        return try {
            Resource.Success(TMDBService.getVideoTv(id).results!!.map {it ->
                it.mapToTvVideo()
            })
        } catch (e: Exception) {

            Resource.Error(e)
        }
    }



}