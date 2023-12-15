package com.selincengiz.ghibli.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_favorite")
data class FavoriteTv(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int?,
    @ColumnInfo("posterPath")
    val posterPath: String?,
    @ColumnInfo("name")
    val name: String?,
)
