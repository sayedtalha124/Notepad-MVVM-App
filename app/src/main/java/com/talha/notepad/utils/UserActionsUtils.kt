package com.talha.notepad.utils

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.talha.notepad.room.Notes

val word = Notes("Welcome to world of words. \nPress the bottom plus button to add some words")

fun View.snackBar(message: String = "Something went wrong") {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    snackbar.setActionTextColor(Color.WHITE)
    snackbar.setAction("Ok") {
        fun onClick(v: View) {
            snackbar.dismiss()
        }
    }.show()
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}