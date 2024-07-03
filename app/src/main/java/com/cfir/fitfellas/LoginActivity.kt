package com.cfir.fitfellas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Check if user is signed in (non-null)
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            // User is signed in, navigate to MainActivity
            startMainActivity(currentUser)
        } else {
            // No user is signed in, start the sign-in process
            createSignInIntent()
        }
    }

    private fun createSignInIntent() {
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        // Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setIsSmartLockEnabled(false) // TODO: Consider enabling this for production
            .setLogo(R.drawable.app_logo)
            .setTheme(R.style.LoginTheme)
            .build()
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser
            Log.d(TAG, "signInWithCredential:success ${user?.uid}")
            startMainActivity(user)
        } else {
            // Sign in failed
            if (response == null) {
                // User cancelled the sign-in flow
                Log.d(TAG, "Sign-in cancelled by user")
                Toast.makeText(this, "Sign-in cancelled", Toast.LENGTH_SHORT).show()
            } else {
                val error = response.error
                Log.e(TAG, "Sign-in error: ${error?.errorCode} - ${error?.message}", error)
                Log.e(TAG, "Provider: ${response.providerType}")
                Toast.makeText(this, "Sign-in failed: Code ${error?.errorCode}", Toast.LENGTH_LONG).show()
            }
            // Reset UI to allow another sign-in attempt
            createSignInIntent()
        }
    }

    private fun startMainActivity(user: FirebaseUser?) {
        if (user == null) {
            Log.e(TAG, "Attempted to start MainActivity with null user")
            Toast.makeText(this, "Sign-in failed. Please try again.", Toast.LENGTH_LONG).show()
            createSignInIntent()
        }
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish()
    }

    companion object {
        private const val TAG = "LoginActivity"
    }
}