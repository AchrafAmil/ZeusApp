package com.achrafamil.zeusapp.common.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracks")
data class TrackEntity(
    @PrimaryKey val id: Int,
    val albumId: Int,
    val title: String,
    val thumbnailUrl: String
)
