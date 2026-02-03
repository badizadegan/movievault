package com.fahimeh.movievault.data.repository

import com.fahimeh.movievault.BuildConfig
import com.fahimeh.movievault.data.local.dao.FavoriteMovieDao
import com.fahimeh.movievault.data.local.mapper.toDomain
import com.fahimeh.movievault.data.local.mapper.toFavoriteEntity
import com.fahimeh.movievault.data.remote.TmdbApi
import com.fahimeh.movievault.data.remote.toDomain
import com.fahimeh.movievault.domain.model.Movie
import com.fahimeh.movievault.domain.model.MovieDetails
import com.fahimeh.movievault.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RemoteMovieRepository(
    private val api: TmdbApi,
    private val favoritesDao: FavoriteMovieDao
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

    override fun observeFavorites(): Flow<List<Movie>> =
        favoritesDao.observeAll().map { list -> list.map { it.toDomain() } }

    override fun observeIsFavorite(movieId: Int): Flow<Boolean> =
        favoritesDao.observeIsFavorite(movieId)

    override suspend fun addToFavorites(movie: Movie) {
        favoritesDao.upsert(movie.toFavoriteEntity())
    }

    override suspend fun addToFavorites(details: MovieDetails) {
        favoritesDao.upsert(details.toFavoriteEntity())
    }

    override suspend fun removeFromFavorites(movieId: Int) {
        favoritesDao.deleteById(movieId)
    }

    override suspend fun toggleFavorite(details: MovieDetails): Boolean {
        val isFav = favoritesDao.isFavorite(details.id)
        return if (isFav) {
            favoritesDao.deleteById(details.id)
            false
        } else {
            favoritesDao.upsert(details.toFavoriteEntity())
            true
        }
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
