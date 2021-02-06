package com.talha.notepad.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.talha.notepad.R
import com.talha.notepad.WordViewModel
import com.talha.notepad.room.Notes
import com.talha.notepad.ui.EditNoteFragment
import com.talha.notepad.ui.MainActivity


class NotesListAdapter(var activity: MainActivity, var viewModel: WordViewModel) :
    ListAdapter<Notes, NotesListAdapter.WordViewHolder>(
        WordsComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {

        val current = getItem(position)
        holder.bind(current)
        holder.itemView.setOnClickListener {

            activity.loadFragment(EditNoteFragment()).let {
                viewModel.setCurrentNote(current)
            }
        }
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: Notes?) {
            wordItemView.text = text!!.word

        }

        companion object {
            fun create(parent: ViewGroup): WordViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return WordViewHolder(view)
            }
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<Notes>() {
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem.word == newItem.word
        }
    }
}
