package com.fahimeh.movievault.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fahimeh.movievault.core.util.UiState
import com.fahimeh.movievault.domain.model.Movie
import com.fahimeh.movievault.ui.components.EmptyView
import com.fahimeh.movievault.ui.components.ErrorView
import com.fahimeh.movievault.ui.components.LoadingView
import com.fahimeh.movievault.ui.components.MovieCard
import com.fahimeh.movievault.ui.design.Dimens
import com.fahimeh.movievault.ui.theme.MovieVaultTheme

@Composable
fun HomeScreen(
    onMovieClick: (Int) -> Unit = {},
    viewModel: HomeViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    HomeContent(
        state = state,
        onMovieClick = onMovieClick,
        onRetry = { viewModel.reload() }
    )
}

@Composable
private fun HomeContent(
    state: UiState<List<Movie>>,
    onMovieClick: (Int) -> Unit,
    onRetry: () -> Unit
) {
    when (state) {
        UiState.Loading -> LoadingView()

        UiState.Empty -> EmptyView(message = "No movies found")

        is UiState.Error -> ErrorView(
            message = state.message,
            onRetry = onRetry
        )

        is UiState.Success -> {
            val movies = state.data

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimens.lg)
            ) {
                Text(
                    text = "Popular Movies",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(Modifier.height(Dimens.lg))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(Dimens.lg),
                    horizontalArrangement = Arrangement.spacedBy(Dimens.lg)
                ) {
                    items(movies, key = { it.id }) { movie ->
                        MovieCard(
                            movie = movie,
                            onClick = { onMovieClick(movie.id) }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MovieVaultTheme {
        HomeContent(
            state = UiState.Success(
                listOf(
                    Movie(1, "Dune: Part Two", "", 8.7, "2024"),
                    Movie(2, "Interstellar", "", 8.6, "2014")
                )
            ),
            onMovieClick = {},
            onRetry = {}
        )
    }
}