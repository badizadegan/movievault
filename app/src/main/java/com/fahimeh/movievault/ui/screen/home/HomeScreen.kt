package com.fahimeh.movievault.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
    state: UiState<HomeUiModel>,
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
            val data = state.data

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = Dimens.lg),
            ) {
                item {
                    Column(modifier = Modifier.padding(horizontal = Dimens.lg)) {
                        MovieSection(
                            title = "Trending",
                            movies = data.trending,
                            onMovieClick = onMovieClick
                        )
                    }
                }

                item { Spacer(Modifier.height(Dimens.xl)) }

                item {
                    Column(modifier = Modifier.padding(horizontal = Dimens.lg)) {
                        MovieSection(
                            title = "Popular",
                            movies = data.popular,
                            onMovieClick = onMovieClick
                        )
                    }
                }

                item { Spacer(Modifier.height(Dimens.xl)) }
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
                HomeUiModel(
                    trending = listOf(
                        Movie(1, "Dune: Part Two", "", 8.7, "2024"),
                        Movie(2, "Interstellar", "", 8.6, "2014")
                    ),
                    popular = listOf(
                        Movie(3, "Joker", "", 8.4, "2019"),
                        Movie(4, "Inception", "", 8.7, "2010")
                    )
                )
            ),
            onMovieClick = {},
            onRetry = {}
        )
    }
}