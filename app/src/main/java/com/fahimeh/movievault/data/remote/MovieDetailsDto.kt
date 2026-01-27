package com.fahimeh.movievault.data.remote

import com.squareup.moshi.Json

data class MovieDetailsDto(
    val id: Int,
    val title: String?,
    @Json(name = "overview") val overview: String?,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "vote_average") val rating: Double?,
    @Json(name = "release_date") val releaseDate: String?,
    @Json(name = "runtime") val runtime: Int?
)