package com.achrafamil.zeusapp.albums

import android.content.Context
import com.achrafamil.zeusapp.R
import com.achrafamil.zeusapp.albums.recycler.AlbumTitleUiModel
import com.achrafamil.zeusapp.albums.recycler.ItemUiModel
import com.achrafamil.zeusapp.albums.recycler.TrackUiModel
import com.achrafamil.zeusapp.common.core.Track
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UiModelMapper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun map(tracks: List<Track>): AlbumsUiModel {
        return AlbumsUiModel(mapTracks(tracks))
    }

    private fun mapTracks(tracks: List<Track>): List<ItemUiModel> {
        var previousItem: Track? = null
        return tracks.flatMap { track ->
            val prefix = if (previousItem?.albumId != track.albumId) {
                AlbumTitleUiModel(
                    id = ALBUM_TITLE_ITEM_ID_PREFIX + track.albumId,
                    text = context.getString(R.string.album_title_format, track.albumId.toString())
                )
            } else null
            previousItem = track

            listOfNotNull(prefix, mapTrack(track))
        }
    }

    private fun mapTrack(track: Track) = TrackUiModel(
        id = TRACK_ITEM_ID_PREFIX + track.id,
        title = track.title,
        thumbnailUrl = track.thumbnailUrl
    )

    companion object {
        private const val TRACK_ITEM_ID_PREFIX = "track_"
        private const val ALBUM_TITLE_ITEM_ID_PREFIX = "album_title_"
    }
}