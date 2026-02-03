package com.fahimeh.movievault.data.local.mapper

import com.fahimeh.movievault.data.local.entity.FavoriteMovieEntity
import com.fahimeh.movievault.domain.model.Movie
import com.fahimeh.movievault.domain.model.MovieDetails

fun Movie.toFavoriteEntity(): FavoriteMovieEntity =
    FavoriteMovieEntity(
        id = id,
        title = title,
        posterUrl = posterUrl,
        rating = rating,
        year = year
    )

fun MovieDetails.toFavoriteEntity(): FavoriteMovieEntity =
    FavoriteMovieEntity(
        id = id,
        title = title,
        posterUrl = posterUrl,
        rating = rating,
        year = year
    )

fun FavoriteMovieEntity.toDomain(): Movie =
    Movie(
        id = id,
        title = title,
        posterUrl = posterUrl,
        rating = rating,
        year = year
    )