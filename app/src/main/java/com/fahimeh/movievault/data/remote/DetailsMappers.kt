package com.fahimeh.movievault.data.remote

import com.fahimeh.movievault.domain.model.CastMember
import com.fahimeh.movievault.domain.model.MovieDetails

private const val IMG = "https://image.tmdb.org/t/p/w500"

fun MovieDetailsDto.toDomain(cast: List<CastMember>): MovieDetails {
    val year = releaseDate?.take(4) ?: "—"
    val genreNames = genres.orEmpty().mapNotNull { it.name }.filter { it.isNotBlank() }

    return MovieDetails(
        id = id,
        title = title.orEmpty(),
        overview = overview.orEmpty(),
        posterUrl = posterPath?.let { "$IMG$it" }.orEmpty(),
        backdropUrl = backdropPath?.let { "$IMG$it" }.orEmpty(),
        rating = rating ?: 0.0,
        year = year,
        runtimeMinutes = runtime ?: 0,
        genres = genreNames,
        cast = cast
    )
}

fun CastDto.toDomain(): CastMember =
    CastMember(
        id = id,
        name = name.orEmpty(),
        profileUrl = profilePath?.let { "$IMG$it" }.orEmpty()
    )
