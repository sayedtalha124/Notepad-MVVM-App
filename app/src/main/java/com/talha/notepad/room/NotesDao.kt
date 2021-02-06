package com.talha.notepad.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface NotesDao {

    @Query("SELECT * FROM word_table ORDER BY word DESC")
    fun getAlphabetizedWords(): Flow<List<Notes>>

    @Update
    suspend fun update(word: Notes)


    @Delete
    suspend fun delete(word: Notes)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Notes)

}

