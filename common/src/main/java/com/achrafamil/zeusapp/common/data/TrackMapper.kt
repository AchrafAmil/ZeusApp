package com.achrafamil.zeusapp.common.data

import com.achrafamil.zeusapp.common.core.Track
import com.achrafamil.zeusapp.common.data.db.TrackEntity
import com.achrafamil.zeusapp.common.data.network.RestTrack
import javax.inject.Inject

class TrackMapper @Inject constructor() {

    fun toTrack(entity: TrackEntity) = Track(
        id = entity.id,
        albumId = entity.albumId,
        title = entity.title,
        thumbnailUrl = entity.thumbnailUrl
    )

    fun toTrackEntity(rest: RestTrack) = TrackEntity(
        id = rest.id,
        albumId = rest.albumId,
        title = rest.title,
        thumbnailUrl = rest.thumbnailUrl
    )
}
