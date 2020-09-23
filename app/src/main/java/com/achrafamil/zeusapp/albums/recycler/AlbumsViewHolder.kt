package com.achrafamil.zeusapp.albums.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.android.synthetic.main.item_album_title.view.*
import kotlinx.android.synthetic.main.item_album_track.view.*

abstract class AlbumsViewHolder<in T : ItemUiModel>(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(uiModel: T)
}

class AlbumTitleViewHolder(
    itemView: View
) : AlbumsViewHolder<AlbumTitleUiModel>(itemView) {
    override fun bind(uiModel: AlbumTitleUiModel) {
        itemView.item_album_title_textview.text = uiModel.text
    }
}

class TrackViewHolder(
    itemView: View
) : AlbumsViewHolder<TrackUiModel>(itemView) {
    override fun bind(uiModel: TrackUiModel) {
        itemView.item_album_track_imageview.load(uiModel.thumbnailUrl)
        itemView.item_album_track_textview.text = uiModel.title
    }
}


class SeparatorViewHolder(
    itemView: View
) : AlbumsViewHolder<SeparatorUiModel>(itemView) {
    override fun bind(uiModel: SeparatorUiModel) {
        /* no-op */
    }
}
