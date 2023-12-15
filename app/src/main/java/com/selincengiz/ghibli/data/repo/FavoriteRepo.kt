package com.selincengiz.ghibli.data.repo

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.google.firebase.auth.FirebaseAuth
import com.selincengiz.ghibli.common.Resource
import com.selincengiz.ghibli.data.entities.FavoriteTv
import com.selincengiz.ghibli.data.source.local.TvDao
import com.selincengiz.ghibli.domain.entities.Tv

import java.lang.Exception

class FavoriteRepo(

    private val tvDao: TvDao,
) {

    suspend fun getFavorites(): Resource<List<FavoriteTv>> {

        return try {
           Resource.Success(tvDao.getFavorites())
        }catch (e:Exception){
            Resource.Error(e)
        }
    }

    suspend fun addFavorites(tv: FavoriteTv) {

        tvDao.addFavorites(tv)
    }


    suspend fun deleteFavorites(tv: FavoriteTv) {
        tvDao.deleteFavorites(tv)
    }

}