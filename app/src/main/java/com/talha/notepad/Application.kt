package com.talha.notepad

import android.app.Application
import com.talha.notepad.repo.NotesRepository
import com.talha.notepad.room.NotesDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class Application : Application() {
    private val coroutineScope = CoroutineScope(SupervisorJob())

    private val database by lazy { NotesDatabase.getDatabase(this, coroutineScope) }
    val repository by lazy { NotesRepository(database.wordDao()) }
}
