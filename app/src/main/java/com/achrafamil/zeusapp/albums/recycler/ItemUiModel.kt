package com.achrafamil.zeusapp.albums.recycler

sealed class ItemUiModel {
    abstract val id: String
}

data class AlbumTitleUiModel(
    override val id: String,
    val text: String
) : ItemUiModel()

data class TrackUiModel(
    override val id: String,
    val title: String,
    val thumbnailUrl: String
) : ItemUiModel()