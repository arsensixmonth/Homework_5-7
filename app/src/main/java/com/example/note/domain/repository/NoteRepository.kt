package com.example.note.domain.repository

import com.example.note.domain.model.Note
import com.example.note.domain.utils.ResultStatus
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun createNote(note: Note):Flow<ResultStatus<Unit>>
    fun delete(note: Note):Flow<ResultStatus<Unit>>
    fun getAllNotes(): Flow<ResultStatus<List<Note>>>
    fun editNote(note: Note):Flow<ResultStatus<Unit>>

}