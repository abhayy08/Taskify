package com.abhay.taskify.features.feature_note.presentation.notes

import com.abhay.taskify.features.feature_note.domain.model.Note


data class NotesState(
    val notes: List<Note> = emptyList()
)