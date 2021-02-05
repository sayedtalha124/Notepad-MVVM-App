package com.talha.notepad.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import com.talha.notepad.ui.MainActivity


fun Activity.loadFragment(fragment: Fragment) {
    val activity = this as MainActivity
    activity.loadFragment(fragment)

}

