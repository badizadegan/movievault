package com.fahimeh.movievault.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fahimeh.movievault.data.local.dao.FavoriteMovieDao
import com.fahimeh.movievault.data.local.entity.FavoriteMovieEntity

@Database(
    entities = [FavoriteMovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieVaultDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}