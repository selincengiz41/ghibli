package com.selincengiz.ghibli.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tv(
    val adult: Boolean?,
    val backdropPath: String?,
    val firstAirDate: String?,
    val genreIds: List<Int>?,
    val id: Int?,
    val mediaType: String?,
    val name: String?,
    val originCountry: List<String>?,
    val originalLanguage: String?,
    val originalName: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val voteAverage: Double?,
    val voteCount: Int?,
    val isFavorite:Boolean?
):Parcelable
