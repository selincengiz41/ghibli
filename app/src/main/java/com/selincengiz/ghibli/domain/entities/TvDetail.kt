package com.selincengiz.ghibli.domain.entities

import com.google.gson.annotations.SerializedName
import com.selincengiz.ghibli.data.entities.Genre
import com.selincengiz.ghibli.data.entities.NextEpisodeToAir
import com.selincengiz.ghibli.data.entities.Season

data class TvDetail(
    val firstAirDate: String?,
    val genres: List<Genre>?,
    val homepage: String?,
    val id: Int?,
    val lastAirDate: Any?,
    val lastEpisodeToAir: Any?,
    val name: String?,
    val nextEpisodeToAir: NextEpisodeToAir?,
    val numberOfEpisodes: Int?,
    val numberOfSeasons: Int?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val seasons: List<Season>?,
    val status: String?,
    val voteAverage: Float?,
    val voteCount: Int?,
    val isFavorite:Boolean?
)
