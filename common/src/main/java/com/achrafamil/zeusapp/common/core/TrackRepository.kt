package com.achrafamil.zeusapp.common.core

import kotlinx.coroutines.flow.Flow

interface TrackRepository {
    fun getTracksFlow(): Flow<List<Track>>
    suspend fun syncTracks()
}
