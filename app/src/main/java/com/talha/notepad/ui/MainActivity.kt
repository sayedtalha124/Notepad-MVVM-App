package com.talha.notepad.ui

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.talha.notepad.*
import com.talha.notepad.databinding.ActivityMainBinding
import com.talha.notepad.utils.createNoteFragment
import com.talha.notepad.utils.editNoteFragment
import com.talha.notepad.utils.homeFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val viewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as Application).repository)
    }
    private val binding by viewBinding(
        ActivityMainBinding::inflate
    )

    var count = 0
    private val fragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        fragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, createNoteFragment, "createNote").hide(createNoteFragment)
            add(R.id.fragment_container, editNoteFragment, "editNote").hide(editNoteFragment)
            add(R.id.fragment_container, homeFragment, "Home")
        }.commit()

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            showFragment(createNoteFragment)

        }
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (count == 0) {
                    showFragment(homeFragment)

                    count++
                } else {
                    finish()

                }

            }
        })
        showFragment(homeFragment)
    }

    var activeFragment: Fragment = homeFragment

    fun showFragment(fragment: Fragment) {
        if (fragment == homeFragment) {
            binding.fab.visibility = View.VISIBLE
        } else {
            binding.fab.visibility = View.GONE

        }

// I did not use beginTransaction().replace because it will create fragment again.
// this hide/show method give app a better performance
        fragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
        activeFragment = fragment
    }

}