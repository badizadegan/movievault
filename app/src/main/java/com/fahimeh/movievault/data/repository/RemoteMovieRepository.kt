package com.fahimeh.movievault.data.repository

import com.fahimeh.movievault.BuildConfig
import com.fahimeh.movievault.data.remote.TmdbApi
import com.fahimeh.movievault.data.remote.toDomain
import com.fahimeh.movievault.domain.model.Movie
import com.fahimeh.movievault.domain.model.MovieDetails
import com.fahimeh.movievault.domain.repository.MovieRepository

class RemoteMovieRepository(
    private val api: TmdbApi
) : MovieRepository {

    override suspend fun getPopularMovies(page: Int): List<Movie> {
        val res = api.getPopularMovies(apiKey = BuildConfig.TMDB_API_KEY, page = page)
        return res.results.orEmpty().map { it.toDomain() }
    }

    override suspend fun getTrendingMovies(page: Int): List<Movie> {
        val res = api.getTrendingMovies(
            apiKey = BuildConfig.TMDB_API_KEY,
            page = page
        )
        return res.results.orEmpty().map { it.toDomain() }
    }

    override suspend fun getMovieDetails(id: Int): MovieDetails {
        val details = api.getMovieDetails(
            movieId = id,
            apiKey = BuildConfig.TMDB_API_KEY
        )

        val credits = api.getMovieCredits(
            movieId = id,
            apiKey = BuildConfig.TMDB_API_KEY
        )

        val cast = credits.cast.orEmpty()
            .take(12)
            .map { it.toDomain() }

        return details.toDomain(cast)
    }
}
