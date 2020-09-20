package com.achrafamil.zeusapp.albums

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.achrafamil.zeusapp.common.core.TrackRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class AlbumsViewModel @ViewModelInject constructor(
    private val trackRepository: TrackRepository,
    private val uiModelMapper: UiModelMapper
) : ViewModel() {

    val uiModel: LiveData<AlbumsUiModel> = trackRepository
        .getTracksFlow()
        .map { tracks -> uiModelMapper.map(tracks) }
        .asLiveData(viewModelScope.coroutineContext)

    init {
        viewModelScope.launch {
            trackRepository.syncTracks()
        }
    }
}
