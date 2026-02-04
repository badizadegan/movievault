package com.fahimeh.movievault.ui.screen.favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.fahimeh.movievault.di.RepositoryModule

@Composable
fun FavoritesRoute(
    onMovieClick: (Int) -> Unit
) {
    val context = LocalContext.current

    val repository = remember {
        RepositoryModule.provideMovieRepository(context)
    }

    val viewModel = remember {
        FavoritesViewModel(repository)
    }

    FavoritesScreen(
        viewModel = viewModel,
        onMovieClick = onMovieClick
    )
}