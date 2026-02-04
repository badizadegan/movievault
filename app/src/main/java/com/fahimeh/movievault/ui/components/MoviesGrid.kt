package com.fahimeh.movievault.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fahimeh.movievault.domain.model.Movie
import com.fahimeh.movievault.ui.design.Dimens

@Composable
fun MoviesGrid(
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit,
    onRemoveClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(Dimens.lg),
        verticalArrangement = Arrangement.spacedBy(Dimens.lg),
        horizontalArrangement = Arrangement.spacedBy(Dimens.lg)
    ) {
        items(
            items = movies,
            key = { it.id }
        ) { movie ->
            Box {
                MovieCard(
                    movie = movie,
                    onClick = { onMovieClick(movie.id) },
                    modifier = Modifier.fillMaxWidth()
                )

                IconButton(
                    onClick = { onRemoveClick(movie.id) },
                    modifier = Modifier
                        .align(androidx.compose.ui.Alignment.TopEnd)
                        .padding(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Remove"
                    )
                }
            }
        }
    }
}