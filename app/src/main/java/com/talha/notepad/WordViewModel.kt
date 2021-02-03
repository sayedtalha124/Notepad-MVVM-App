package com.talha.notepad

import androidx.lifecycle.*
import com.talha.notepad.repo.WordRepository
import com.talha.notepad.room.Notes
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository) : ViewModel() {
    var getCurrentNote = MutableLiveData<Notes>()
    fun setCurrentNote(note: Notes) {
        getCurrentNote.postValue(note)
    }

    val allWords: LiveData<List<Notes>> = repository.allWords.asLiveData()


    fun insert(word: Notes) = viewModelScope.launch {
        repository.insert(word)
    }

    fun update(it: Notes) = viewModelScope.launch {
        repository.update(it)
    }

    fun delete(it: Notes) = viewModelScope.launch {
        repository.delete(it)
    }

    var itemEdit = MutableLiveData<Notes>()
    fun forEditItem(note: Notes) {
        itemEdit.postValue(note)

    }


}

class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
