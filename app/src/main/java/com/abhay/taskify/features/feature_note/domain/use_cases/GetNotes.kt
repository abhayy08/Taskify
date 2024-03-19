package com.abhay.taskify.features.feature_note.domain.use_cases

import com.abhay.taskify.features.feature_note.domain.model.Note
import com.abhay.taskify.features.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow


class GetNotes(
    private val repository: NoteRepository
) {
    operator fun invoke(
    ): Flow<List<Note>> {
        return repository.getNotes()
    }
}