package com.fahimeh.movievault.ui.screen.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.fahimeh.movievault.core.util.UiState
import com.fahimeh.movievault.domain.model.MovieDetails
import com.fahimeh.movievault.ui.design.Dimens

@Composable
fun DetailsScreen(
    movieId: Int,
    viewModel: DetailsViewModel = viewModel()
    ) {

    val state by viewModel.uiState.collectAsState()

    var contentVisible by remember { mutableStateOf(false) }

    LaunchedEffect(movieId) {
        contentVisible = false
        viewModel.loadMovieDetails(movieId)
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

        UiState.Empty -> {
            Text(
                text = "No details available",
                modifier = Modifier.padding(Dimens.lg)
            )
        }

        is UiState.Success -> {
            val details = (state as UiState.Success<MovieDetails>).data

            LaunchedEffect(details.id) {
                contentVisible = true
            }

            Column(modifier = Modifier.fillMaxSize()) {
                AnimatedVisibility(
                    visible = contentVisible,
                    enter = fadeIn(tween(260))
                ) {
                    Box(modifier = Modifier.height(420.dp)) {
                        AsyncImage(
                            model = details.posterUrl,
                            contentDescription = details.title,
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
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(Dimens.lg)
                ) {
                    AnimatedVisibility(
                        visible = contentVisible,
                        enter = fadeIn(tween(260, delayMillis = 80)) +
                                slideInVertically(tween(260, delayMillis = 80)) { it / 6 }
                    ) {
                        Text(
                            text = details.title,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }

                    Spacer(Modifier.height(Dimens.sm))

                    AnimatedVisibility(
                        visible = contentVisible,
                        enter = fadeIn(tween(260, delayMillis = 140)) +
                                slideInVertically(tween(260, delayMillis = 140)) { it / 6 }
                    ) {
                        Text(
                            text = "${details.year} • ⭐ ${details.rating}",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }

                    Spacer(Modifier.height(Dimens.lg))

                    AnimatedVisibility(
                        visible = contentVisible,
                        enter = fadeIn(tween(260, delayMillis = 220)) +
                                slideInVertically(tween(260, delayMillis = 220)) { it / 6 }
                    ) {
                        Text(
                            text = "This is a placeholder description. Later we will load the real overview from TMDB API.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}