package com.selincengiz.ghibli.di

import android.content.Context
import androidx.room.Room

import com.selincengiz.ghibli.data.source.local.TvRoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDBModule {

    @Provides
    @Singleton
    fun provideRoomDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, TvRoomDB::class.java, "tv_room_db").build()

    @Provides
    @Singleton
    fun provideRoomDao(roomDB: TvRoomDB) = roomDB.tvDao()
}