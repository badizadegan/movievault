package com.fahimeh.movievault.ui.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahimeh.movievault.core.util.UiState
import com.fahimeh.movievault.data.remote.RetrofitClient
import com.fahimeh.movievault.data.repository.FakeMovieRepository
import com.fahimeh.movievault.data.repository.RemoteMovieRepository
import com.fahimeh.movievault.domain.model.Movie
import com.fahimeh.movievault.domain.model.MovieDetails
import com.fahimeh.movievault.domain.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: MovieRepository = RemoteMovieRepository(api = RetrofitClient.api)
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<MovieDetails>>(UiState.Loading)
    val uiState: StateFlow<UiState<MovieDetails>> = _uiState

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val details = repository.getMovieDetails(movieId)
                _uiState.value = UiState.Success(details)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}