package com.achrafamil.zeusapp.common.data

import com.achrafamil.zeusapp.common.core.Track
import com.achrafamil.zeusapp.common.core.TrackRepository
import com.achrafamil.zeusapp.common.data.db.TrackDao
import com.achrafamil.zeusapp.common.data.network.LeboncoinService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrackRepositoryImpl @Inject constructor(
    private val trackDao: TrackDao,
    private val service: LeboncoinService,
    private val trackMapper: TrackMapper
) : TrackRepository {

    override fun getTracksFlow(): Flow<List<Track>> {
        return trackDao
            .getTracksFlow()
            .map { tracks -> tracks.map(trackMapper::toTrack) }
    }

    override suspend fun syncTracks() = withContext(Dispatchers.IO) {
        val networkTracks = service.getTracks()
        val mappedTracks = networkTracks.map(trackMapper::toTrackEntity).toTypedArray()
        trackDao.insertTracks(*mappedTracks)
    }
}
