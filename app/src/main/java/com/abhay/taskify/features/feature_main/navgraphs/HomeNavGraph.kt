package com.abhay.taskify.features.feature_main.navgraphs

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.abhay.taskify.features.feature_main.BottomBarScreen
import com.abhay.taskify.features.feature_note.presentation.navigation.noteNavGraph
import com.abhay.taskify.features.feature_note.presentation.notes.NotesScreen
import com.abhay.taskify.features.feature_reminders.ReminderScreen
import com.abhay.taskify.features.feature_tasks.TaskScreen

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Notes.route,
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
        composable(route = BottomBarScreen.Tasks.route) {
            TaskScreen()
        }

        composable(
            route = BottomBarScreen.Notes.route
        ) {
            NotesScreen(
                navController = navController,
                paddingValues = paddingValues,
                onFabClick = {
                    navController.navigate(Graph.NOTES)
                }
            )
        }
        composable(route = BottomBarScreen.Reminders.route) {
            ReminderScreen()
        }
        noteNavGraph(navController = navController)
    }
}

