package com.example.note.presentation.notes

sealed class UIState<T> {
    class Loading<T> : UIState<T>()
    class Success<T>(val data: T?) : UIState<T>()
    class Error<T>(val msg: String) : UIState<T>()
    class Empty<T> : UIState<T>()

}