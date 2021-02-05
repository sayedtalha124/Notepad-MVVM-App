package com.talha.notepad.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.talha.notepad.R
import com.talha.notepad.databinding.FragmentLoginBinding
import com.talha.notepad.utils.SessionManager
import com.talha.notepad.utils.SessionManager.Companion.EMAIL
import com.talha.notepad.utils.SessionManager.Companion.USERNAME
import com.talha.notepad.utils.SessionManager.Companion.isLogin
import com.talha.notepad.utils.SessionManager.Companion.userPhoto
import com.talha.notepad.utils.loadFragment
import com.talha.notepad.utils.snackBar
import com.talha.notepad.viewBinding


class LoginFragment : Fragment(R.layout.fragment_login) {
    private val RC_SIGN_IN = 0

    private val binding by viewBinding(FragmentLoginBinding::bind)
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail().requestProfile()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
        binding.signInButton.setOnClickListener {
            signIn()
        }
        binding.signInButton.setSize(SignInButton.SIZE_WIDE);
        signIn()
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            updateUI(account!!)
        } catch (e: ApiException) {
            binding.signInButton.snackBar()

        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val sessionManager = SessionManager(requireContext())
        sessionManager.putString(userPhoto, account.photoUrl.toString())
        sessionManager.putString(USERNAME, account.displayName)
        sessionManager.putString(EMAIL, account.email)
        sessionManager.putBoolValue(isLogin, true)
        requireActivity().loadFragment(HomeFragment())
    }
}