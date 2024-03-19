package com.abhay.taskify.features.feature_main.navgraphs

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.abhay.taskify.features.feature_main.HomeScreen

@Composable
fun RootNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Graph.HOME,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        },
        popEnterTransition = {
            EnterTransition.None
        },
        popExitTransition = {
            ExitTransition.None
        }
    ) {
        composable(route = Graph.HOME) {
            HomeScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val NOTES = "notes_graph"
}