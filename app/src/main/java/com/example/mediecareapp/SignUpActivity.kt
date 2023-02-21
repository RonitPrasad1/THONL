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
import com.example.MediECareApp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

// Sign Up Page Class:
class SignUpActivity : AppCompatActivity()
{
    // Binding:
    private lateinit var binding : ActivitySignUpBinding

    // ActionBar:
    private lateinit var actionBar : ActionBar

    // ProgressDialog (Old code - Deprecated):
    private lateinit var progressDialog: ProgressDialog

    // Firebase Auth:
    private lateinit var firebaseAuth : FirebaseAuth

    // Data Variables:
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Essentially a back button:
        actionBar = supportActionBar!!
        actionBar.title = "THONL"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        // Configure Progress Dialog:
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait...")
        progressDialog.setMessage("Creating an account...")
        progressDialog.setCanceledOnTouchOutside(false)

        // Firebase Authentication:
        firebaseAuth = FirebaseAuth.getInstance()

        // This handles the Sign Up button:
        binding.SignUpButton.setOnClickListener()
        {
            validateData()
        }

        binding.HaveAnAccountRedirect.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    // Validates the data for the Email and Password:
    private fun validateData()
    {
        email = binding.EmailEditText.text.toString().trim()
        password = binding.passwordEditText.text.toString().trim()

        if (TextUtils.isEmpty(email))
        {
            binding.EmailEditText.error = "You can not leave the email field blank!"
        }
        else if (TextUtils.isEmpty(password))
        {
            binding.passwordEditText.error = "You can not leave the password field blank!"
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            binding.EmailEditText.error = "Invalid Email Formatting, please use a proper format!"
        }
        else if (password.length < 7)
        {
            binding.passwordEditText.error = "Password must be at least 7 characters in length!"
        }
        else if (password.length >= 15)
        {
            binding.passwordEditText.error = "Password cannot be greater than or equal to 15 characters!"
        }
        else
        {
            firebaseSignUp()
        }
    }

    // Sign Up function for Firebase:
    private fun firebaseSignUp()
    {
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                progressDialog.dismiss()

                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Account created with email $email", Toast.LENGTH_SHORT).show()

                finish()
            }
            .addOnFailureListener { e->
                progressDialog.dismiss()
                Toast.makeText(this, "Sign Up Failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onSupportNavigateUp(): Boolean
    {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}