package com.selincengiz.ghibli.di

import com.selincengiz.ghibli.data.repo.AuthRepoImpl
import com.selincengiz.ghibli.domain.repo.AuthRepo
import com.selincengiz.ghibli.domain.usecase.LoginUseCase
import com.selincengiz.ghibli.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule  {

    @Provides
    @Singleton
    fun provideLoginUseCase(authRepo: AuthRepo) =
        LoginUseCase(authRepo = authRepo)

    @Provides
    @Singleton
    fun provideRegisterUseCase(authRepo: AuthRepo) =
        RegisterUseCase(authRepo = authRepo)

}