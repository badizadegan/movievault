package com.fahimeh.movievault.data.repository

import com.fahimeh.movievault.domain.model.Movie
import com.fahimeh.movievault.domain.model.MovieDetails
import com.fahimeh.movievault.domain.repository.MovieRepository

class FakeMovieRepository : MovieRepository {

    private val movies = listOf(
        Movie(1, "Dune: Part Two", "https://image.tmdb.org/t/p/w500/8b8R8l88Qje9dn9OE8PY05Nxl1X.jpg", 8.7, "2024"),
        Movie(2, "Joker", "https://image.tmdb.org/t/p/w500/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg", 8.4, "2019"),
        Movie(3, "Interstellar", "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg", 8.6, "2014")
    )

    override suspend fun getPopularMovies(page: Int): List<Movie> = movies

    override suspend fun getMovieDetails(id: Int): MovieDetails {
        val movie = movies.find { it.id == id } ?: movies.first()

        return MovieDetails(
            id = movie.id,
            title = movie.title,
            overview = "Fake overview for now (will be replaced by TMDB).",
            posterUrl = movie.posterUrl,
            backdropUrl = movie.posterUrl,
            rating = movie.rating,
            year = movie.year,
            runtimeMinutes = 120,
            cast = emptyList()
        )
    }
}