package com.abhay.taskify.features.feature_note.presentation.add_edit_notes_screen

import androidx.compose.ui.focus.FocusState


sealed class AddEditNoteEvent{
    data class onTitleChange(val value: String) : AddEditNoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditNoteEvent()
    data class onNoteChange(val value: String) : AddEditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState) : AddEditNoteEvent()
    object SaveNote: AddEditNoteEvent()
    object DeleteNote: AddEditNoteEvent()
}


