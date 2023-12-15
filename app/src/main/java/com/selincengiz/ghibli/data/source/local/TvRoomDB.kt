package com.selincengiz.ghibli.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.selincengiz.ghibli.data.entities.FavoriteTv
import com.selincengiz.ghibli.domain.entities.Tv


@Database(entities = [FavoriteTv::class], version = 1)
abstract class TvRoomDB : RoomDatabase() {

    abstract fun tvDao(): TvDao
}