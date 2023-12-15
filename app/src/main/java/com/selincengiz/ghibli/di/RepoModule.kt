package com.selincengiz.ghibli.di


import com.google.firebase.auth.FirebaseAuth
import com.selincengiz.ghibli.data.source.remote.TMDBService
import com.selincengiz.ghibli.data.repo.AuthRepo
import com.selincengiz.ghibli.data.repo.FavoriteRepo
import com.selincengiz.ghibli.data.repo.TvRepo
import com.selincengiz.ghibli.data.source.local.TvDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideTvRepo(TMDBService: TMDBService) =
        TvRepo(TMDBService = TMDBService)

    @Provides
    @Singleton
    fun provideAuthRepo(auth: FirebaseAuth) =
        AuthRepo(auth = auth)

    @Provides
    @Singleton
    fun provideFavoriteRepo(tvDao: TvDao) =
        FavoriteRepo(tvDao = tvDao)

}