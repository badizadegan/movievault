package com.fahimeh.movievault.ui.screen.details

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.fahimeh.movievault.ui.components.EmptyView
import com.fahimeh.movievault.ui.components.ErrorView
import com.fahimeh.movievault.ui.components.LoadingView
import com.fahimeh.movievault.ui.design.Dimens

@SuppressLint("DefaultLocale")
@Composable
fun DetailsScreen(
    movieId: Int,
    viewModel: DetailsViewModel = viewModel(),
    onBack: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()
    var contentVisible by remember { mutableStateOf(false) }

    LaunchedEffect(movieId) {
        contentVisible = false
        viewModel.loadMovieDetails(movieId)
    }

    when (state) {
        UiState.Loading -> LoadingView()

        UiState.Empty -> EmptyView("No details available")

        is UiState.Error -> {
            val error = state as UiState.Error
            ErrorView(
                message = error.message,
                onRetry = { viewModel.loadMovieDetails(movieId) }
            )
        }

        is UiState.Success -> {
            val details = (state as UiState.Success<MovieDetails>).data
            val runtimeText =
                if (details.runtimeMinutes > 0) "${details.runtimeMinutes} min" else "—"
            val ratingText = String.format("%.1f", details.rating)

            LaunchedEffect(details.id) { contentVisible = true }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                // HEADER
                AnimatedVisibility(
                    visible = contentVisible,
                    enter = fadeIn(animationSpec = tween(260))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(420.dp)
                    ) {
                        AsyncImage(
                            model = details.backdropUrl.ifBlank { details.posterUrl },
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

                        // Back button overlay
                        IconButton(
                            onClick = onBack,
                            modifier = Modifier
                                .statusBarsPadding()
                                .padding(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    }
                }

                // CONTENT
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
                            text = "${details.year} • $runtimeText • ⭐ $ratingText",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }

                    Spacer(Modifier.height(Dimens.md))

                    AnimatedVisibility(
                        visible = contentVisible,
                        enter = fadeIn(tween(260, delayMillis = 180)) +
                                slideInVertically(tween(260, delayMillis = 180)) { it / 6 }
                    ) {
                        if (details.genres.isNotEmpty()) {
                            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                items(details.genres) { genre ->
                                    AssistChip(
                                        onClick = { },
                                        label = { Text(genre) }
                                    )
                                }
                            }
                        }
                    }

                    Spacer(Modifier.height(Dimens.lg))

                    AnimatedVisibility(
                        visible = contentVisible,
                        enter = fadeIn(tween(260, delayMillis = 220)) +
                                slideInVertically(tween(260, delayMillis = 220)) { it / 6 }
                    ) {
                        Text(
                            text = details.overview.ifBlank { "No overview available." },
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Spacer(Modifier.height(Dimens.xl))

                    AnimatedVisibility(
                        visible = contentVisible,
                        enter = fadeIn(tween(260, delayMillis = 280)) +
                                slideInVertically(tween(260, delayMillis = 280)) { it / 6 }
                    ) {
                        if (details.cast.isNotEmpty()) {
                            Column {
                                Text(
                                    text = "Cast",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Spacer(Modifier.height(Dimens.md))

                                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                    items(details.cast) { person ->
                                        Column {
                                            AsyncImage(
                                                model = person.profileUrl,
                                                contentDescription = person.name,
                                                modifier = Modifier.size(64.dp),
                                                contentScale = ContentScale.Crop
                                            )
                                            Spacer(Modifier.height(6.dp))
                                            Text(
                                                text = person.name,
                                                style = MaterialTheme.typography.labelSmall,
                                                maxLines = 1
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Spacer(Modifier.height(24.dp))
                }
            }
        }
    }
}