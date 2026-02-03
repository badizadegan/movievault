package com.fahimeh.movievault.ui.screen.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahimeh.movievault.core.util.UiState
import com.fahimeh.movievault.domain.model.Movie
import com.fahimeh.movievault.domain.repository.MovieRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FavoritesViewModel(
    repository: MovieRepository
) : ViewModel() {

    val uiState: StateFlow<UiState<List<Movie>>> =
        repository.observeFavorites()
            .map { list ->
                if (list.isEmpty()) UiState.Empty
                else UiState.Success(list)
            }
            .catch { emit(UiState.Error("Failed to load favorites")) }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                UiState.Loading
            )
}
