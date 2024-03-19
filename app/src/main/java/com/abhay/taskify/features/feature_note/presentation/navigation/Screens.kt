package com.abhay.taskify.features.feature_note.presentation.navigation

sealed class NotesScreens(
    val route: String
) {
    data object NotesMainScreen : NotesScreens(route = "NOTES_SCREEN")
    data object AddEditScreen : NotesScreens(route = "ADD_EDIT_SCREEN")
}