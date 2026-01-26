package com.fahimeh.movievault.ui.screen.home

import androidx.lifecycle.ViewModel
import com.fahimeh.movievault.core.util.UiState
import com.fahimeh.movievault.data.repository.FakeMovieRepository
import com.fahimeh.movievault.domain.model.Movie

class HomeViewModel(
    private val repository: FakeMovieRepository = FakeMovieRepository()
) : ViewModel() {

    val uiState: UiState<List<Movie>> =
        UiState.Success(repository.getMovies())
}