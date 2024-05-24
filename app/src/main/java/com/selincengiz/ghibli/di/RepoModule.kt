package com.selincengiz.ghibli.di


import com.google.firebase.auth.FirebaseAuth
import com.selincengiz.ghibli.data.source.remote.TMDBService
import com.selincengiz.ghibli.data.repo.AuthRepoImpl
import com.selincengiz.ghibli.data.repo.FavoriteRepoImpl
import com.selincengiz.ghibli.data.repo.TvRepoImpl
import com.selincengiz.ghibli.data.source.local.TvDao
import com.selincengiz.ghibli.domain.repo.AuthRepo
import com.selincengiz.ghibli.domain.repo.FavoriteRepo
import com.selincengiz.ghibli.domain.repo.TvRepo
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
    fun provideTvRepo(TMDBService: TMDBService): TvRepo =
        TvRepoImpl(TMDBService = TMDBService)

    @Provides
    @Singleton
    fun provideAuthRepo(auth: FirebaseAuth): AuthRepo =
        AuthRepoImpl(auth = auth)

    @Provides
    @Singleton
    fun provideFavoriteRepo(tvDao: TvDao): FavoriteRepo =
        FavoriteRepoImpl(tvDao = tvDao)

}