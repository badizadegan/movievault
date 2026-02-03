package com.fahimeh.movievault.domain.repository

import com.fahimeh.movievault.domain.model.Movie
import com.fahimeh.movievault.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovies(page: Int = 1): List<Movie>
    suspend fun getTrendingMovies(page: Int = 1): List<Movie>
    suspend fun getMovieDetails(id: Int): MovieDetails

    fun observeFavorites(): Flow<List<Movie>>
    fun observeIsFavorite(movieId: Int): Flow<Boolean>

    suspend fun addToFavorites(movie: Movie)
    suspend fun addToFavorites(details: MovieDetails)
    suspend fun removeFromFavorites(movieId: Int)
    suspend fun toggleFavorite(details: MovieDetails): Boolean
}