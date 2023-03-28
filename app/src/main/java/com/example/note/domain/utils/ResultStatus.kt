package com.example.note.domain.utils

sealed class  ResultStatus<T>(
    val data: T? = null,
    val error: String = "Unknown error"
) {
    class Succes<T>(data: T?) : ResultStatus<T>(data = data)
    class Loading<T>() : ResultStatus<T>()
    class Error<T>(msg: String) : ResultStatus<T>(error = msg)
}

