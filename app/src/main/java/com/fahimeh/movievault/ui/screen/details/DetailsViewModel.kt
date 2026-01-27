package com.fahimeh.movievault.ui.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahimeh.movievault.core.util.UiState
import com.fahimeh.movievault.data.repository.FakeMovieRepository
import com.fahimeh.movievault.domain.model.Movie
import com.fahimeh.movievault.domain.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: MovieRepository = FakeMovieRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Movie>>(UiState.Loading)
    val uiState: StateFlow<UiState<Movie>> = _uiState

    fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val movie = repository.getMovieById(movieId)
                _uiState.value = movie?.let { UiState.Success(it) }
                    ?: UiState.Error("Movie not found")
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}