package com.achrafamil.zeusapp.albums

import android.content.Context
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.achrafamil.zeusapp.common.core.TrackRepository
import com.achrafamil.zeusapp.utils.toast
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class AlbumsViewModel @ViewModelInject constructor(
    private val trackRepository: TrackRepository,
    private val uiModelMapper: UiModelMapper,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _isLoading = MutableLiveData(false)

    val isLoading: LiveData<Boolean> = _isLoading
    val uiModel: LiveData<AlbumsUiModel> = trackRepository
        .getTracksFlow()
        .map { tracks -> uiModelMapper.map(tracks) }
        .asLiveData(viewModelScope.coroutineContext)

    init {
        triggerSync()
    }

    fun onRefresh() = triggerSync()

    private fun triggerSync() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                trackRepository.syncTracks()
            } catch (exception: Exception) {
                val message = "Failed to sync with server, check internet."
                Log.e(LOG_TAG, message, exception)
                context.toast(message)
            } finally {
                _isLoading.value = false
            }
        }
    }

    companion object {
        private const val LOG_TAG = "AlbumsViewModel"
    }
}
