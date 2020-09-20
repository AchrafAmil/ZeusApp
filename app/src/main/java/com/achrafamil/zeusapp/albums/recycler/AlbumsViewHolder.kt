package com.achrafamil.zeusapp.albums.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
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
        (itemView as TextView).text = uiModel.text
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
