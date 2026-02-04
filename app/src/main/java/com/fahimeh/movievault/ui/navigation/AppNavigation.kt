package com.fahimeh.movievault.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fahimeh.movievault.ui.screen.banner.BannerScreen
import com.fahimeh.movievault.ui.screen.details.DetailsRoute
import com.fahimeh.movievault.ui.screen.details.DetailsScreen
import com.fahimeh.movievault.ui.screen.favorites.FavoritesRoute
import com.fahimeh.movievault.ui.screen.favorites.FavoritesScreen
import com.fahimeh.movievault.ui.screen.home.HomeRoute
import com.fahimeh.movievault.ui.screen.home.HomeScreen
import com.fahimeh.movievault.ui.screen.search.SearchScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
    ) {

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
            HomeRoute(
                onMovieClick = { id -> navController.navigate(Route.details(id)) }
            )
        }

        composable(Route.SEARCH) {
            SearchScreen()
        }

        composable(Route.FAVORITES) {
            FavoritesRoute(
                onMovieClick = { id -> navController.navigate(Route.details(id)) }
            )
        }

        composable(
            route = "${Route.DETAILS}/{movieId}",
            arguments = listOf(
                navArgument("movieId") { type = NavType.IntType }
            )
        ) { backStackEntry ->

            val movieId = backStackEntry.arguments?.getInt("movieId") ?: return@composable

            DetailsRoute(
                movieId = movieId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}