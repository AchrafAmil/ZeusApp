package com.achrafamil.zeusapp.common.data.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RestTrack(
    val id: Int,
    val albumId: Int,
    val title: String,
    val thumbnailUrl: String
)
