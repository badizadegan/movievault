package com.fahimeh.movievault.domain.repository

import com.fahimeh.movievault.domain.model.Movie
import com.fahimeh.movievault.domain.model.MovieDetails

interface MovieRepository {
    suspend fun getPopularMovies(page: Int = 1): List<Movie>
    suspend fun getTrendingMovies(page: Int = 1): List<Movie>
    suspend fun getMovieDetails(id: Int): MovieDetails
}