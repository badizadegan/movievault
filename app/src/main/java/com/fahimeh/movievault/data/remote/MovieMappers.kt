package com.fahimeh.movievault.data.remote

import com.fahimeh.movievault.domain.model.Movie

private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500"

fun MovieDto.toDomain(): Movie {
    val year = releaseDate?.take(4) ?: "—"
    val posterUrl = posterPath?.let { "$POSTER_BASE_URL$it" } ?: ""

    return Movie(
        id = id,
        title = title.orEmpty(),
        posterUrl = posterUrl,
        rating = rating ?: 0.0,
        year = year
    )
}