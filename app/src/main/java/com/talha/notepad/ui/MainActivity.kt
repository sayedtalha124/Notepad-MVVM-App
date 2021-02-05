package com.talha.notepad.ui

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.talha.notepad.R
import com.talha.notepad.databinding.ActivityMainBinding
import com.talha.notepad.utils.SessionManager
import com.talha.notepad.utils.SessionManager.Companion.isLogin
import com.talha.notepad.viewBinding


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(
        ActivityMainBinding::inflate
    )

    private val fragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            loadFragment(CreateNoteFragment())

        }

        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {


            }
        })
        val sessionManager = SessionManager(this)

        if (sessionManager.getBoolValue(isLogin))
            loadFragment(HomeFragment())
        else
            loadFragment(LoginFragment())

    }

    override fun onBackPressed() {

        val fragment = fragmentManager.findFragmentById(R.id.fragment_container)

        if (fragment is HomeFragment || fragment is LoginFragment) {
            //  fab.snackBar("Press again to exit")
            finish()
        } else {
            fragmentManager.popBackStack()
        }
    }

    fun loadFragment(fragment: Fragment) {
        if (fragment is CreateNoteFragment) {
            binding.fab.hide()

        } else {
            binding.fab.show()
        }
        val fragmentData =
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
        if (fragment is HomeFragment || fragment is LoginFragment) {
            fragmentData.commit()

        } else {

            fragmentData.addToBackStack(
                null
            ).commit()

        }

    }


}