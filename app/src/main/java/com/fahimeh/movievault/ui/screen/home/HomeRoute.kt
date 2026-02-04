package com.fahimeh.movievault.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.fahimeh.movievault.di.RepositoryModule

@Composable
fun HomeRoute(
    onMovieClick: (Int) -> Unit
) {
    val context = LocalContext.current

    val repository = remember {
        RepositoryModule.provideMovieRepository(context)
    }

    val viewModel = remember {
        HomeViewModel(repository)
    }

    HomeScreen(
        viewModel = viewModel,
        onMovieClick = onMovieClick
    )
}