package com.selincengiz.ghibli.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.selincengiz.ghibli.data.entities.FavoriteTv
import com.selincengiz.ghibli.domain.entities.Tv

@Dao
interface TvDao {

    @Query("SELECT * FROM tv_favorite ")
    suspend fun getFavorites(): List<FavoriteTv>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorites(tv: FavoriteTv)

    @Delete
    suspend fun deleteFavorites(tv: FavoriteTv)

    @Query("DELETE  FROM tv_favorite")
    suspend fun nukeTable()

}