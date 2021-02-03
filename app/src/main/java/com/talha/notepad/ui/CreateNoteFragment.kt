package com.talha.notepad.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.talha.notepad.*
import com.talha.notepad.databinding.FragmentCreateNoteBinding
import com.talha.notepad.room.Notes
import com.talha.notepad.utils.homeFragment
import com.talha.notepad.utils.showFragment
import com.talha.notepad.utils.snackBar


class CreateNoteFragment : Fragment(R.layout.fragment_create_note) {
    private lateinit var editWordView: EditText

    private val binding by viewBinding(FragmentCreateNoteBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editWordView = binding.editWord
        val viewModel = ViewModelProvider(
            requireActivity(),
            WordViewModelFactory((requireActivity().application as Application).repository)
        ).get(WordViewModel::class.java)
        val button = binding.buttonSave
        button.setOnClickListener {
            if (TextUtils.isEmpty(editWordView.text)) {
                button.snackBar("Give some words to save")

            } else {
                val word = editWordView.text.toString()
                val note = Notes(word)
                editWordView.text.clear()
                viewModel.insert(note).let {
                    requireActivity().showFragment(homeFragment)
                }
            }

        }
    }

}