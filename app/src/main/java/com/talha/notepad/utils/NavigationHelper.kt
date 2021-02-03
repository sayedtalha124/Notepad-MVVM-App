package com.talha.notepad.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import com.talha.notepad.ui.CreateNoteFragment
import com.talha.notepad.ui.EditNoteFragment
import com.talha.notepad.ui.HomeFragment
import com.talha.notepad.ui.MainActivity

fun Activity.showFragment(fragment: Fragment) {
    val activity = this as MainActivity
    activity.showFragment(fragment)

}

val homeFragment = HomeFragment()
val createNoteFragment = CreateNoteFragment()
val editNoteFragment = EditNoteFragment()