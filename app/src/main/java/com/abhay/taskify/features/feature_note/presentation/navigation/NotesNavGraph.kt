package com.abhay.taskify.features.feature_note.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.abhay.taskify.features.feature_main.navgraphs.Graph
import com.abhay.taskify.features.feature_note.presentation.add_edit_notes_screen.AddEditNotesScreen

fun NavGraphBuilder.noteNavGraph(
    navController: NavHostController, paddingValues: PaddingValues = PaddingValues(20.dp)
) {
    navigation(
        route = Graph.NOTES, startDestination = NotesScreens.AddEditScreen.route
    ) {
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