package com.abhay.taskify.features.feature_main.navgraphs

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.abhay.taskify.features.feature_main.FeaturesScreens
import com.abhay.taskify.features.feature_note.presentation.add_edit_notes_screen.AddEditNotesScreen
import com.abhay.taskify.features.feature_note.presentation.navigation.NotesScreens
import com.abhay.taskify.features.feature_note.presentation.notes.NotesUiScreen
import com.abhay.taskify.features.feature_reminders.ReminderScreen
import com.abhay.taskify.features.feature_tasks.TaskScreen

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = FeaturesScreens.Notes.route,
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
        composable(route = FeaturesScreens.Tasks.route) {
            TaskScreen()
        }

        composable(route = FeaturesScreens.Notes.route) {
            NotesUiScreen(navController = navController, paddingValues = paddingValues)
        }
        composable(route = FeaturesScreens.Reminders.route) {
            ReminderScreen()
        }
        composable(route = NotesScreens.AddEditScreen.route + "?noteId={noteId}",
            arguments = listOf(navArgument(
                name = "noteId"
            ) {
                type = NavType.IntType
                defaultValue = -1
            })) {
            AddEditNotesScreen(
                navController = navController,
            )
        }
    }
}

