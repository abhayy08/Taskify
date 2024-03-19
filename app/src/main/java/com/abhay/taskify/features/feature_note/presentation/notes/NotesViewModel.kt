package com.abhay.taskflow.features.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhay.taskflow.features.feature_note.domain.model.Note
import com.abhay.taskflow.features.feature_note.domain.use_case.NotesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesUseCases: NotesUseCases
) : ViewModel() {
    private val _state = mutableStateOf(NotesState())
    val notesState: State<NotesState> = _state
    private var recentlyDeletedNote: Note? = null
    private var getNotesJob: Job? = null

    init {
        GetNotes()
    }

    fun onEvent(event: NotesEvent) {
        when (event) {

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    notesUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }

            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    notesUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }

            is NotesEvent.AddNote -> {
                viewModelScope.launch {
                    notesUseCases.addNote(event.note)
                }
            }
        }
    }

    fun GetNotes() {
        getNotesJob?.cancel()
        getNotesJob = notesUseCases.getNotes()
            .onEach { notes ->
                _state.value = _state.value.copy(
                    notes = notes
                )
            }
            .launchIn(viewModelScope)

    }
}