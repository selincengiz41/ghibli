package com.selincengiz.ghibli.domain.repo

import com.selincengiz.ghibli.common.Resource
import com.selincengiz.ghibli.data.entities.FavoriteTv

interface FavoriteRepo {
    suspend fun getFavorites(): Resource<List<FavoriteTv>>
    suspend fun addFavorites(tv: FavoriteTv)
    suspend fun deleteFavorites(tv: FavoriteTv)
}