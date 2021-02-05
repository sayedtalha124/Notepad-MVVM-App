package com.talha.notepad.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.talha.notepad.*
import com.talha.notepad.databinding.FragmentEditNoteBinding
import com.talha.notepad.room.Notes
import com.talha.notepad.utils.loadFragment
import com.talha.notepad.utils.snackBar


class EditNoteFragment : Fragment(R.layout.fragment_edit_note) {
    private val binding by viewBinding(FragmentEditNoteBinding::bind)
    private lateinit var viewModel: WordViewModel
    private lateinit var editWordView: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(),
            WordViewModelFactory((requireActivity().application as Application).repository)
        ).get(WordViewModel::class.java)
        var notes: Notes? = null
        editWordView = binding.editWord
        viewModel.getCurrentNote.observe(viewLifecycleOwner, {
            notes = it
            editWordView.setText(notes!!.word)

        })


        val buttonSave = binding.buttonSave
        buttonSave.setOnClickListener {
            if (TextUtils.isEmpty(editWordView.text)) {
                buttonSave.snackBar("Give some words to save")

            } else {
                val word = editWordView.text.toString()
                notes!!.word = word
                notes?.let { it1 ->
                    viewModel.update(it1).let {
                        requireActivity().loadFragment(HomeFragment())
                    }
                }
            }

        }
        val buttonDelete = binding.buttonDelete
        buttonDelete.setOnClickListener {

            notes?.let { it1 ->
                viewModel.delete(it1).let {
                    requireActivity().loadFragment(HomeFragment())
                }
            }

        }
    }


}