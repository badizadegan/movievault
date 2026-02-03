package com.fahimeh.movievault.di

import android.content.Context
import com.fahimeh.movievault.data.repository.RemoteMovieRepository
import com.fahimeh.movievault.domain.repository.MovieRepository

object RepositoryModule {

    fun provideMovieRepository(context: Context): MovieRepository {
        val api = NetworkModule.provideTmdbApi()
        val db = DatabaseModule.provideDatabase(context)
        val favoriteDao = DatabaseModule.provideFavoriteDao(db)

        return RemoteMovieRepository(
            api = api,
            favoritesDao = favoriteDao
        )
    }
}