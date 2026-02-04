package com.fahimeh.movievault.di

import com.fahimeh.movievault.data.remote.RetrofitClient
import com.fahimeh.movievault.data.remote.TmdbApi

object NetworkModule {
    fun provideTmdbApi(): TmdbApi = RetrofitClient.api
}