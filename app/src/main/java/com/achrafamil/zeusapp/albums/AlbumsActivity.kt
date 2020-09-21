package com.achrafamil.zeusapp.albums

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.achrafamil.zeusapp.R
import com.achrafamil.zeusapp.albums.recycler.AlbumsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_albums.*
import javax.inject.Inject

@AndroidEntryPoint
class AlbumsActivity : AppCompatActivity() {
    private val viewModel: AlbumsViewModel by viewModels()

    @Inject
    lateinit var albumsAdapter: AlbumsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)
        setupRecycler()
        setupSwipeToRefresh()
        observeViewModel()
    }

    private fun setupRecycler() = with(albums_recycler) {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        adapter = albumsAdapter
        preserveFocusAfterLayout = false
        albums_fast_scroller.setRecyclerView(this)
        addOnScrollListener(albums_fast_scroller.onScrollListener)
    }

    private fun setupSwipeToRefresh() {
        albums_swipe_refresh.setOnRefreshListener {
            viewModel.onRefresh()
        }
    }

    private fun observeViewModel() {
        viewModel.uiModel.observe(this) { uiModel ->
            albumsAdapter.submitList(uiModel.items)
        }
        viewModel.isLoading.observe(this) { isLoading ->
            albums_swipe_refresh.isRefreshing = isLoading
        }
    }
}
