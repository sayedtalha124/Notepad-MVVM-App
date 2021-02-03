package com.talha.notepad.repo

import androidx.annotation.WorkerThread
import com.talha.notepad.room.Notes
import com.talha.notepad.room.NotesDao
import kotlinx.coroutines.flow.Flow


class WordRepository(private val wordDao: NotesDao) {


    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<Notes>> = wordDao.getAlphabetizedWords()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Notes) {
        wordDao.insert(word)
    }

    suspend fun delete(it: Notes) {
        wordDao.delete(it)
    }


    suspend fun update(it: Notes) {
        wordDao.update(it)
    }

}