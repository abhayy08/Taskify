package com.abhay.taskflow.features.feature_note.domain.use_case

import com.abhay.taskflow.features.feature_note.domain.use_case.AddNote
import com.abhay.taskflow.features.feature_note.domain.use_case.DeleteNote
import com.abhay.taskflow.features.feature_note.domain.use_case.GetNote
import com.abhay.taskflow.features.feature_note.domain.use_case.GetNotes

data class NotesUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNote: GetNote
)
