package com.selincengiz.ghibli.data.entities


import com.google.gson.annotations.SerializedName

data class TvVideoResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("results")
    val results: List<ResultX>?
)