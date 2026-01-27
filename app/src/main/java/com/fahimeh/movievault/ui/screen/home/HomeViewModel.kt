package com.fahimeh.movievault.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahimeh.movievault.core.util.UiState
import com.fahimeh.movievault.data.remote.RetrofitClient
import com.fahimeh.movievault.data.repository.RemoteMovieRepository
import com.fahimeh.movievault.domain.model.Movie
import com.fahimeh.movievault.domain.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repo: MovieRepository = RemoteMovieRepository(api = RetrofitClient.api)
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Movie>>> = _uiState

    init {
        loadPopular()
    }

    private fun loadPopular() {
        viewModelScope.launch {
            try {
                val movies = repo.getPopularMovies()
                _uiState.value = UiState.Success(movies)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}