package com.example.note.presentation.fillingNotes

import com.example.note.data.base.BaseViewModel
import com.example.note.domain.model.Note
import com.example.note.domain.usecase.CreateNoteUseCase
import com.example.note.domain.usecase.EditNoteUseCase
import com.example.note.presentation.notes.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FillingNotesViewModel @Inject constructor(
    private val editNoteUseCase: EditNoteUseCase,
    private val createNoteUseCase: CreateNoteUseCase,

    ) : BaseViewModel() {

    private val _editNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val editNoteStade = _editNoteState.asStateFlow()

    private val _createNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val createNoteState = _createNoteState.asStateFlow()


    fun create(title: String, desc: String) {
        if (title.isNotBlank() && desc.isNotBlank()) {
            createNoteUseCase(
                Note(
                    title = title,
                    description = desc,
                    createdAt = System.currentTimeMillis()
                )
            ).collectFlow(_createNoteState)
        } else {
            _createNoteState.value = UIState.Error(msg = "fill the gaps")
        }
    }

    fun update(note: Note) {
        if (note.title.isNotBlank() && note.description.isNotBlank()) {
            editNoteUseCase(
                note
            ).collectFlow(_editNoteState)
        } else {
            _editNoteState.value = UIState.Error(msg = "fill the gaps")
        }
    }


}