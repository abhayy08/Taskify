package com.abhay.taskify.features.feature_note.presentation.add_edit_notes_screen

data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
