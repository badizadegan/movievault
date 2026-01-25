package com.fahimeh.movievault.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fahimeh.movievault.domain.model.Movie
import com.fahimeh.movievault.ui.design.Dimens

@Composable
fun MovieCard(
    movie: Movie,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .width(140.dp)
    ) {
        AsyncImage(
            model = movie.posterUrl,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(210.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(Dimens.cardRadius))
        )

        Spacer(Modifier.height(Dimens.sm))

        Text(
            text = movie.title,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = "${movie.year} • ⭐ ${movie.rating}",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
    }
}
