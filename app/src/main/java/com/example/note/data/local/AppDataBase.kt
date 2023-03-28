package com.example.note.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.note.data.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}