package com.fahimeh.movievault.di

import android.content.Context
import androidx.room.Room
import com.fahimeh.movievault.data.local.db.MovieVaultDatabase

object DatabaseModule {

    @Volatile
    private var database: MovieVaultDatabase? = null

    fun provideDatabase(context: Context): MovieVaultDatabase =
        database ?: synchronized(this) {
            database ?: Room.databaseBuilder(
                context.applicationContext,
                MovieVaultDatabase::class.java,
                "movievault.db"
            ).build().also { database = it }
        }

    fun provideFavoriteDao(db: MovieVaultDatabase) =
        db.favoriteMovieDao()
}