package com.fahimeh.movievault.ui.screen.home

import com.fahimeh.movievault.domain.model.Movie

data class HomeUiModel(
    val trending: List<Movie>,
    val popular: List<Movie>
)
