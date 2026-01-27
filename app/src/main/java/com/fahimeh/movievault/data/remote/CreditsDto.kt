package com.fahimeh.movievault.data.remote

import com.squareup.moshi.Json

data class CreditsDto(
    @Json(name = "cast") val cast: List<CastDto>?
)

data class CastDto(
    val id: Int,
    val name: String?,
    @Json(name = "profile_path") val profilePath: String?
)