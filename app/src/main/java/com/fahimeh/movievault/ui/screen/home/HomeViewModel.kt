package com.fahimeh.movievault.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahimeh.movievault.core.util.UiState
import com.fahimeh.movievault.data.remote.RetrofitClient
import com.fahimeh.movievault.data.repository.RemoteMovieRepository
import com.fahimeh.movievault.domain.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repo: MovieRepository = RemoteMovieRepository(api = RetrofitClient.api)
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<UiState<HomeUiModel>>(UiState.Loading)
    val uiState: StateFlow<UiState<HomeUiModel>> = _uiState

    init {
        loadHome()
    }

    private fun loadHome() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val trending = repo.getTrendingMovies()
                val popular = repo.getPopularMovies()

                _uiState.value =
                    if (trending.isEmpty() && popular.isEmpty()) {
                        UiState.Empty
                    }
                    else {
                        UiState.Success(
                            HomeUiModel(
                                trending = trending,
                                popular = popular
                            )
                        )
                    }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?:"Failed to load home movies")
            }
        }
    }
    fun reload() {
        loadHome()
    }
}