package com.fahimeh.movievault.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fahimeh.movievault.ui.screen.banner.BannerScreen
import com.fahimeh.movievault.ui.screen.home.HomeScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.BANNER,
        modifier = modifier
    ) {
        composable(Route.BANNER) {
            BannerScreen(
                onGetStarted = {
                    navController.navigate(Route.HOME) {
                        popUpTo(Route.BANNER) { inclusive = true }
                    }
                }
            )
        }

        composable(Route.HOME) {
            HomeScreen()
        }
    }
}