package com.abhay.taskify.di

import android.app.Application
import androidx.room.Room
import com.abhay.taskify.features.feature_note.data.data_source.NoteDao
import com.abhay.taskflow.features.feature_note.data.data_source.NoteDatabase
import com.abhay.taskify.features.feature_note.data.repository.NoteRepositoryImpl
import com.abhay.taskify.features.feature_note.domain.repository.NoteRepository
import com.abhay.taskify.features.feature_note.domain.use_cases.AddNote
import com.abhay.taskify.features.feature_note.domain.use_cases.DeleteNote
import com.abhay.taskify.features.feature_note.domain.use_cases.GetNote
import com.abhay.taskify.features.feature_note.domain.use_cases.GetNotes
import com.abhay.taskify.features.feature_note.domain.use_cases.NotesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.NOTEDATABASENAME
        ).build()
    }

    @Provides
    fun provideNotesDao(db: NoteDatabase): NoteDao {
        return db.noteDao
    }

    @Provides
    @Singleton
    fun provideNoteRepository(dao: NoteDao): NoteRepository {
        return NoteRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideNotesUseCases(repository: NoteRepositoryImpl): NotesUseCases {
        return NotesUseCases(
            getNotes = GetNotes(repository),
            getNote = GetNote(repository),
            addNote = AddNote(repository),
            deleteNote = DeleteNote(repository)
        )
    }
}