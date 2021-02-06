package com.talha.notepad.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.talha.notepad.*
import com.talha.notepad.adapter.NotesListAdapter
import com.talha.notepad.databinding.HomeFragmentBinding
import com.talha.notepad.utils.SpacesItemDecoration
import com.talha.notepad.utils.toast

class HomeFragment : Fragment(R.layout.home_fragment) {
    private lateinit var viewModel: WordViewModel
    private val binding by viewBinding(HomeFragmentBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(),
            WordViewModelFactory((requireActivity().application as Application).repository)
        ).get(WordViewModel::class.java)

        val recyclerView = binding.recyclerview
        val adapter = NotesListAdapter(requireActivity() as MainActivity, viewModel = viewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(SpacesItemDecoration())
        viewModel.allWords.observe(viewLifecycleOwner, { words ->

            words?.let {
                if (it.isEmpty()) {
                    requireContext().toast("No Notes available")
                    //viewModel.insert(word)
                }
                adapter.submitList(it)

            }
        })
    }


}