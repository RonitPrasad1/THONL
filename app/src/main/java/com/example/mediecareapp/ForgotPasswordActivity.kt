// Imports/Packages:
package com.example.MediECareApp
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.MediECareApp.databinding.ActivityForgotPasswordBinding

import com.google.firebase.auth.FirebaseAuth

// Forgot Password Class:
class ForgotPasswordActivity : AppCompatActivity()
{
    // Binding:
    private lateinit var binding: ActivityForgotPasswordBinding

    private lateinit var progressDialog: ProgressDialog

    // ActionBar:
    private lateinit var actionBar: ActionBar

    // Data Variable:
    private var currentEmail = ""
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title = "THONL"

        firebaseAuth = FirebaseAuth.getInstance()

        // This handles the Log in button:
        binding.ConfirmButton.setOnClickListener {
            forgotPassword()
        }

        // This redirects us back to the login page once you've done the "forgot password" check:
        binding.LoginTextView.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    // Validates the data for the Email:
    private fun forgotPassword()
    {
        currentEmail = binding.EditEmailText.text.toString().trim()
        if (TextUtils.isEmpty(currentEmail))
        {
            binding.EditEmailText.error =
                "You can not leave the email field blank, please retry again!"
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(currentEmail).matches())
        {
            binding.EditEmailText.error = "Invalid Email Formatting, please use a proper format!"
        }
        else
        {
            progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Resetting your Password")
            progressDialog.setMessage("Please wait while we are resetting your password...")
            progressDialog.setCanceledOnTouchOutside(false)
            progressDialog.show()

            // Update password for the current user:
            firebaseAuth.sendPasswordResetEmail(currentEmail).addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(this@ForgotPasswordActivity, "Password reset email sent!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                finish()
            }.addOnFailureListener {
                progressDialog.dismiss()
                Toast.makeText(this@ForgotPasswordActivity, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}