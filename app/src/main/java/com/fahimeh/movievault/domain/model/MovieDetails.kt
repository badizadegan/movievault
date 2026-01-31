package com.fahimeh.movievault.domain.model

data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String,
    val backdropUrl: String,
    val rating: Double,
    val year: String,
    val runtimeMinutes: Int,
    val genres: List<String>,
    val cast: List<CastMember>
)

data class CastMember(
    val id: Int,
    val name: String,
    val profileUrl: String
)