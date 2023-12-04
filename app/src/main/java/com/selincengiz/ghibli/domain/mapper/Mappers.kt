package com.selincengiz.ghibli.domain.mapper

import com.selincengiz.ghibli.data.entities.Result
import com.selincengiz.ghibli.data.entities.TvDetailRespond
import com.selincengiz.ghibli.domain.entities.Tv
import com.selincengiz.ghibli.domain.entities.TvDetail


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
        voteCount,
        false
    )
}


fun TvDetailRespond.mapToTvDetail(): TvDetail {
    return TvDetail(
        firstAirDate,
        genres,
        homepage,
        id,
        lastAirDate,
        lastEpisodeToAir,
        name,
        nextEpisodeToAir,
        numberOfEpisodes,
        numberOfSeasons,
        overview,
        popularity,
        posterPath,
        seasons,
        status,
        voteAverage,
        voteCount,
        false
    )
}


