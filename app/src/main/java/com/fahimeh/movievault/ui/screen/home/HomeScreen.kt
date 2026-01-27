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
import com.fahimeh.movievault.core.util.UiState
import com.fahimeh.movievault.domain.model.Movie
import com.fahimeh.movievault.ui.components.MovieCard
import com.fahimeh.movievault.ui.design.Dimens
import com.fahimeh.movievault.ui.theme.MovieVaultTheme

@Composable
fun HomeScreen(
    onMovieClick: (Int) -> Unit = {},
    viewModel: HomeViewModel = HomeViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    when (state) {
        UiState.Loading -> {
            Text(text = "Loading...")
        }

        is UiState.Error -> {
            Text((state as UiState.Error).message)
        }

        is UiState.Success -> {
            val movies = (state as UiState.Success<List<Movie>>).data

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
                    items(movies) { movie ->
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
        HomeScreen()
    }
}