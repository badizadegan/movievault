package com.fahimeh.movievault.ui.navigation

object Route {
    const val BANNER = "banner"
    const val HOME = "home"
    const val DETAILS = "details"

    fun details(movieId: Int) = "$DETAILS/$movieId"
}