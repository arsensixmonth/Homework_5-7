package com.example.note.domain.usecase

import com.example.note.domain.model.Note
import com.example.note.domain.repository.NoteRepository
import javax.inject.Inject

class CreateNoteUseCase@Inject constructor(private val noteRepository: NoteRepository) {
    operator fun invoke(note: Note) = noteRepository.createNote(note)

}