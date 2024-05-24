package com.selincengiz.ghibli.data.repo

import com.selincengiz.ghibli.common.Resource
import com.selincengiz.ghibli.data.entities.FavoriteTv
import com.selincengiz.ghibli.data.source.local.TvDao
import com.selincengiz.ghibli.domain.repo.FavoriteRepo

import java.lang.Exception

class FavoriteRepoImpl(

    private val tvDao: TvDao,
) :FavoriteRepo{

    override suspend fun getFavorites(): Resource<List<FavoriteTv>> {

        return try {
           Resource.Success(tvDao.getFavorites())
        }catch (e:Exception){
            Resource.Error(e)
        }
    }

    override suspend fun addFavorites(tv: FavoriteTv) {

        tvDao.addFavorites(tv)
    }


    override suspend fun deleteFavorites(tv: FavoriteTv) {
        tvDao.deleteFavorites(tv)
    }

}