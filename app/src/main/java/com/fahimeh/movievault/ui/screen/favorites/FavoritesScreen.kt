package com.fahimeh.movievault.ui.screen.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.fahimeh.movievault.core.util.UiState
import com.fahimeh.movievault.ui.components.EmptyView
import com.fahimeh.movievault.ui.components.ErrorView
import com.fahimeh.movievault.ui.components.LoadingView
import com.fahimeh.movievault.ui.components.MoviesGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel,
    onMovieClick: (Int) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Favorites") }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (val s = state) {
                UiState.Loading -> LoadingView()

                UiState.Empty -> EmptyView("No favorites yet")

                is UiState.Error -> ErrorView(
                    message = s.message,
                    onRetry = { viewModel.refresh() }
                )

                is UiState.Success -> {
                    MoviesGrid(
                        movies = s.data,
                        onMovieClick = onMovieClick,
                        onRemoveClick = { movieId -> viewModel.removeFavorite(movieId) }
                    )
                }
            }
        }
    }
}