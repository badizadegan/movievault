package com.fahimeh.movievault.ui.screen.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.fahimeh.movievault.core.util.UiState
import com.fahimeh.movievault.ui.components.EmptyView
import com.fahimeh.movievault.ui.components.ErrorView
import com.fahimeh.movievault.ui.components.LoadingView
import com.fahimeh.movievault.ui.components.MoviesGrid

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel,
    onMovieClick: (Int) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    when (val s = state) {
        UiState.Loading -> LoadingView()
        UiState.Empty -> EmptyView("No favorites yet")
        is UiState.Error -> ErrorView(message = s.message, onRetry = { /* optional */ })
        is UiState.Success -> {
            MoviesGrid(
                movies = s.data,
                onMovieClick = onMovieClick
            )
        }
    }
}