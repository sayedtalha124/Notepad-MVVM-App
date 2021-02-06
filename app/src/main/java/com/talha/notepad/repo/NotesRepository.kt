package com.talha.notepad.repo

import androidx.annotation.WorkerThread
import com.talha.notepad.room.Notes
import com.talha.notepad.room.NotesDao
import kotlinx.coroutines.flow.Flow


class NotesRepository(private val wordDao: NotesDao) {


    val allWords: Flow<List<Notes>> = wordDao.getAlphabetizedWords()


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