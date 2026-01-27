package com.fahimeh.movievault.ui.screen.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fahimeh.movievault.core.util.UiState
import com.fahimeh.movievault.data.repository.FakeMovieRepository
import com.fahimeh.movievault.domain.model.Movie
import com.fahimeh.movievault.ui.design.Dimens

@Composable
fun DetailsScreen(
    movieId: Int,
    viewModel: DetailsViewModel = DetailsViewModel()
    ) {

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.loadMovie(movieId)
    }

    when (state) {
        UiState.Loading -> {
            Text(
                text = "Loading...",
                modifier = Modifier.padding(Dimens.lg)
            )
        }

        is UiState.Error -> {
            Text(
                text = (state as UiState.Error).message,
                modifier = Modifier.padding(Dimens.lg)
            )
        }

        is UiState.Success -> {
            val movie = (state as UiState.Success<Movie>).data

            Column(modifier = Modifier.fillMaxSize()) {

                Box(modifier = Modifier.height(420.dp)) {
                    AsyncImage(
                        model = movie.posterUrl,
                        contentDescription = movie.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        MaterialTheme.colorScheme.background
                                    )
                                )
                            )
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(Dimens.lg)
                ) {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(Modifier.height(Dimens.sm))

                    Text(
                        text = "${movie.year} • ⭐ ${movie.rating}",
                        style = MaterialTheme.typography.labelSmall
                    )

                    Spacer(Modifier.height(Dimens.lg))

                    Text(
                        text = "This is a placeholder description. Later we will load the real overview from TMDB API.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}