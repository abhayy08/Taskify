package com.abhay.taskflow.features.feature_note.presentation.add_edit_notes

import androidx.compose.ui.focus.FocusState
import com.abhay.taskflow.features.feature_note.domain.model.Note

sealed class AddEditNoteEvent{
    data class onTitleChange(val value: String) :AddEditNoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditNoteEvent()
    data class onNoteChange(val value: String) : AddEditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState) : AddEditNoteEvent()
    object SaveNote: AddEditNoteEvent()
    object DeleteNote: AddEditNoteEvent()
}


