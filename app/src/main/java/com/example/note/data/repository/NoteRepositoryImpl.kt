package com.example.note.data.repository

import com.example.note.data.base.BaseRepository
import com.example.note.data.local.NoteDao
import com.example.note.data.mappers.toEntity
import com.example.note.data.mappers.toNote
import com.example.note.domain.model.Note
import com.example.note.domain.repository.NoteRepository
import javax.inject.Inject


class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository, BaseRepository() {
    override fun createNote(note: Note) = doRequest {
        noteDao.createNote(note.toEntity())
    }


    override fun getAllNotes() = doRequest {
        noteDao.getAllNotes().map { it.toNote() }
    }

    override fun delete(note: Note) = doRequest {
        noteDao.deleteNote(note.toEntity())
    }


    override fun editNote(note: Note) = doRequest {
        noteDao.editNote(note.toEntity())
    }
}