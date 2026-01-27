package com.fahimeh.movievault.data.remote

import com.squareup.moshi.Json

data class PopularMoviesResponse(
    @Json(name = "results") val results: List<MovieDto>?
)

data class MovieDto(
    val id: Int,
    val title: String?,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "vote_average") val rating: Double?,
    @Json(name = "release_date") val releaseDate: String?
)