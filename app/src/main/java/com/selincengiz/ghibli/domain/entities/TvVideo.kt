package com.selincengiz.ghibli.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvVideo (
    val id: String?,
    val iso31661: String?,
    val iso6391: String?,
    val key: String?,
    val name: String?,
    val official: Boolean?,
    val publishedAt: String?,
    val site: String?,
    val size: Int?,
    val type: String?,
    var photo: String?
        ):Parcelable