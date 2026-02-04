package com.fahimeh.movievault.ui.screen.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.fahimeh.movievault.di.RepositoryModule

@Composable
fun DetailsRoute(
    movieId: Int,
    onBack: () -> Unit
) {
    val context = LocalContext.current

    val repository = remember {
        RepositoryModule.provideMovieRepository(context)
    }

    val viewModel = remember {
        DetailsViewModel(repository)
    }

    DetailsScreen(
        movieId = movieId,
        viewModel = viewModel,
        onBack = onBack
    )
}