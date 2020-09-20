package com.achrafamil.zeusapp.albums

import android.content.Context
import com.achrafamil.zeusapp.R
import com.achrafamil.zeusapp.albums.recycler.AlbumTitleUiModel
import com.achrafamil.zeusapp.albums.recycler.ItemUiModel
import com.achrafamil.zeusapp.albums.recycler.SeparatorUiModel
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
        return tracks
            .flatMap { track ->
                val previousIsDifferentAlbum = previousItem?.albumId != track.albumId
                val albumTitle = if (previousIsDifferentAlbum) {
                    AlbumTitleUiModel(
                        id = ALBUM_TITLE_ITEM_ID_PREFIX + track.albumId,
                        text = context.getString(
                            R.string.album_title_format,
                            track.albumId.toString()
                        )
                    )
                } else null
                val separator = if (previousIsDifferentAlbum && previousItem != null) {
                    SeparatorUiModel(ALBUM_SEPARATOR_ITEM_ID_PREFIX + track.albumId)
                } else null

                previousItem = track
                listOfNotNull(separator, albumTitle, mapTrack(track))
            }
            .plus(SeparatorUiModel(LAST_SEPARATOR_ID))
    }

    private fun mapTrack(track: Track) = TrackUiModel(
        id = TRACK_ITEM_ID_PREFIX + track.id,
        title = track.title,
        thumbnailUrl = track.thumbnailUrl
    )

    companion object {
        private const val TRACK_ITEM_ID_PREFIX = "track_"
        private const val ALBUM_TITLE_ITEM_ID_PREFIX = "album_title_"
        private const val ALBUM_SEPARATOR_ITEM_ID_PREFIX = "separator_"
        private const val LAST_SEPARATOR_ID = "separator_last"
    }
}