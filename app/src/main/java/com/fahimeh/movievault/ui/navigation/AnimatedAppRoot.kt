package com.fahimeh.movievault.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedAppRoot() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute =
        backStackEntry?.destination?.route?.substringBefore("/") ?: Route.BANNER

    val showBottomBar = currentRoute in setOf(Route.HOME, Route.SEARCH, Route.FAVORITES)

    Scaffold(
        bottomBar = {
            if (showBottomBar) BottomNavBar(navController)
        }
    ) { innerPadding ->

        @Suppress("UNUSED_PARAMETER")
        AnimatedContent(
            targetState = currentRoute,
            transitionSpec = {
                // Slide + Fade
                val duration = 260
                (slideInHorizontally(tween(duration)) { it / 6 } + fadeIn(tween(duration)))
                    .togetherWith(
                        slideOutHorizontally(tween(duration)) { -it / 6 } + fadeOut(tween(duration))
                    )
            },
            label = "nav-anim"
        ) {
            AppNavigation(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
