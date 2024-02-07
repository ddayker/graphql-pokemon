package com.dayker.graphqlpokemon.core.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dayker.graphqlpokemon.core.navigation.AppNavGraph.DETAILS_SCREEN_ROUTE
import com.dayker.graphqlpokemon.core.navigation.AppNavGraph.HOME_SCREEN_ROUTE
import com.dayker.graphqlpokemon.core.navigation.AppNavGraph.NAV_GRAPH_ROUTE
import com.dayker.graphqlpokemon.core.navigation.AppNavGraph.POKEMON_ID_PARAM
import com.dayker.graphqlpokemon.presentation.details.DetailsScreen
import com.dayker.graphqlpokemon.presentation.home.HomeScreen

@Composable
fun appNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        route = NAV_GRAPH_ROUTE,
        startDestination = HOME_SCREEN_ROUTE
    ) {
        composable(
            route = HOME_SCREEN_ROUTE,
        ) {
            HomeScreen(
                navController = navController
            )
        }
        composable(
            route = "$DETAILS_SCREEN_ROUTE/{$POKEMON_ID_PARAM}",
            arguments = listOf(
                navArgument(POKEMON_ID_PARAM) {
                    type = NavType.IntType
                }
            ),
            enterTransition = {
                scaleInAnimation()
            },
            popEnterTransition = {
                scaleInAnimation()
            },
            exitTransition = {
                scaleOutAnimation()
            },
            popExitTransition = {
                scaleOutAnimation()
            }
        ) {
            DetailsScreen(navController = navController)
        }
    }
}

object AppNavGraph {
    const val NAV_GRAPH_ROUTE = "nav_graph"
    const val HOME_SCREEN_ROUTE = "home"
    const val DETAILS_SCREEN_ROUTE = "details"
    const val POKEMON_ID_PARAM = "pokemonId"
}

fun scaleInAnimation() =
    scaleIn(
        animationSpec = tween(
            easing = FastOutSlowInEasing
        )
    )

fun scaleOutAnimation() =
    scaleOut(
        animationSpec = tween(
            easing = LinearOutSlowInEasing
        )
    )