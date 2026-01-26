package com.fahimeh.movievault.domain.repository

import com.fahimeh.movievault.domain.model.Movie

interface MovieRepository {
    fun getMovies(): List<Movie>
    fun getMovieById(id: Int): Movie?
}