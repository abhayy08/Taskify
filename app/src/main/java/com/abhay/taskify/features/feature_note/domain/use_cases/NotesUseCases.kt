package com.abhay.taskify.features.feature_note.domain.use_cases

import com.abhay.taskify.features.feature_note.domain.use_cases.AddNote
import com.abhay.taskify.features.feature_note.domain.use_cases.DeleteNote
import com.abhay.taskify.features.feature_note.domain.use_cases.GetNote
import com.abhay.taskify.features.feature_note.domain.use_cases.GetNotes

data class NotesUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNote: GetNote
)
