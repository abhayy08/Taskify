package com.abhay.taskify.features.feature_note.presentation.notes

import com.abhay.taskify.features.feature_note.domain.model.Note


sealed class NotesEvent {
    data class AddNote(val note: Note) : NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    object RestoreNote : NotesEvent()
}