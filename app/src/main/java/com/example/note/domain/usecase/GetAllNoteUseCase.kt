package com.example.note.domain.usecase

import com.example.note.domain.model.Note
import com.example.note.domain.repository.NoteRepository
import com.example.note.domain.utils.ResultStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNoteUseCase@Inject constructor(private val noteRepository: NoteRepository) {
   operator fun invoke() : Flow<ResultStatus<List<Note>>>{
        return noteRepository.getAllNotes()
    }

}