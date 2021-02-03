package com.talha.notepad.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "word_table")
class Notes(
    @ColumnInfo(name = "word") var word: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Serializable

