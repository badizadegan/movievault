package com.fahimeh.movievault.ui.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedAppRoot() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute =
        backStackEntry?.destination?.route?.substringBefore("/") ?: Route.BANNER

    AnimatedContent(
        targetState = currentRoute,
        transitionSpec = {
            fadeIn(tween(220)) togetherWith fadeOut(tween(220))
        },
        label = "nav-anim"
    ) {
        AppNavigation(navController = navController)
    }
}
