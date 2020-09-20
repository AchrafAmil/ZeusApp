package com.achrafamil.zeusapp.albums.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.achrafamil.zeusapp.R
import javax.inject.Inject


class AlbumsAdapter @Inject constructor(
) : ListAdapter<ItemUiModel, AlbumsViewHolder<*>>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumsViewHolder<*> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ALBUM_TITLE_TYPE ->
                AlbumTitleViewHolder(inflater.inflate(R.layout.item_album_title, parent, false))
            TRACK_TYPE ->
                TrackViewHolder(inflater.inflate(R.layout.item_album_track, parent, false))
            else -> throw IllegalArgumentException("viewType not recognized: $viewType")
        }
    }

    @Suppress("UNCHECKED_CAST") // Adapter is conceived to be both producer and consumer of VH‍
    override fun onBindViewHolder(holder: AlbumsViewHolder<*>, position: Int) {
        (holder as AlbumsViewHolder<ItemUiModel>).bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is AlbumTitleUiModel -> ALBUM_TITLE_TYPE
            is TrackUiModel -> TRACK_TYPE
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemUiModel>() {
            override fun areItemsTheSame(oldItem: ItemUiModel, newItem: ItemUiModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ItemUiModel,
                newItem: ItemUiModel
            ): Boolean = oldItem == newItem
        }

        private const val ALBUM_TITLE_TYPE = 0
        private const val TRACK_TYPE = 1
    }
}