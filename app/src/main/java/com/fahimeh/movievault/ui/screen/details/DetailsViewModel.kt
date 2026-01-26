package com.fahimeh.movievault.ui.screen.details

import androidx.lifecycle.ViewModel
import com.fahimeh.movievault.core.util.UiState
import com.fahimeh.movievault.data.repository.FakeMovieRepository
import com.fahimeh.movievault.domain.model.Movie

class DetailsViewModel(
    private val repository: FakeMovieRepository = FakeMovieRepository()
) : ViewModel() {

    fun loadMovie(movieId: Int): UiState<Movie> {
        val movie = repository.getMovieById(movieId)
        return movie?.let {
            UiState.Success(it)
        } ?: UiState.Error("Movie not found")
    }
}