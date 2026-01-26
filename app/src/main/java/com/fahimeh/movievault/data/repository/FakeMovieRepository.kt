package com.fahimeh.movievault.data.repository

import com.fahimeh.movievault.domain.model.Movie
import com.fahimeh.movievault.domain.repository.MovieRepository

class FakeMovieRepository : MovieRepository {

    private val movies = listOf(
        Movie(
            id = 1,
            title = "Dune: Part Two",
            posterUrl = "https://image.tmdb.org/t/p/w500/8b8R8l88Qje9dn9OE8PY05Nxl1X.jpg",
            rating = 8.7,
            year = "2024"
        ),
        Movie(
            id = 2,
            title = "Joker",
            posterUrl = "https://image.tmdb.org/t/p/w500/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
            rating = 8.4,
            year = "2019"
        ),
        Movie(
            id = 3,
            title = "Interstellar",
            posterUrl = "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg",
            rating = 8.6,
            year = "2014"
        )
    )

    override fun getMovies(): List<Movie> = movies

    override fun getMovieById(id: Int): Movie? =
        movies.find { it.id == id }
}