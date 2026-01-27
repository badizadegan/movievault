package com.fahimeh.movievault.domain.repository

import com.fahimeh.movievault.domain.model.Movie

interface MovieRepository {
    suspend fun getPopularMovies(page: Int = 1): List<Movie>
    suspend fun getMovieById(id: Int): Movie?
}