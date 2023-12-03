package com.selincengiz.ghibli.domain.mapper

import com.selincengiz.ghibli.data.entities.Result
import com.selincengiz.ghibli.domain.entities.Tv



fun Result.mapToTv(): Tv {
    return Tv(
        adult,
        backdropPath,
        firstAirDate,
        genreIds,
        id,
        mediaType,
        name,
        originCountry,
        originalLanguage,
        originalName,
        overview,
        popularity,
        posterPath,
        voteAverage,
        voteCount
    )
}


