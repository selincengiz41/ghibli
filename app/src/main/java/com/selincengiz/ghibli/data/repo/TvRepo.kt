package com.selincengiz.ghibli.data.repo


import com.selincengiz.ghibli.common.Resource
import com.selincengiz.ghibli.data.api.TMDBService
import java.lang.Exception
import com.selincengiz.ghibli.domain.entities.Tv
import com.selincengiz.ghibli.domain.mapper.mapToTv

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

}