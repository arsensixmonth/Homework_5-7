package com.example.note.data.base

import com.example.note.domain.utils.ResultStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

abstract class BaseRepository {
    protected fun <T> doRequest(request: suspend () -> T) = flow {
        emit(ResultStatus.Loading())
        try {
            val getAll = request()
            emit(ResultStatus.Succes(getAll))
        } catch (e: IOException) {
            emit(ResultStatus.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}