package com.achrafamil.zeusapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.achrafamil.zeusapp.albums.AlbumsViewModel
import com.achrafamil.zeusapp.common.core.TrackRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AlbumsViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `view model creation should get tracks flow and trigger sync`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            // given
            val mockedRepository: TrackRepository = mock()

            // when
            val viewModel = AlbumsViewModel(mockedRepository, mock(), mock())

            // then
            verify(mockedRepository, times(1)).syncTracks()
            verify(mockedRepository, times(1)).getTracksFlow()
        }
}
