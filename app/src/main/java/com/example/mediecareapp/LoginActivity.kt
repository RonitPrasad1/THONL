// Imports/Packages:
package com.example.MediECareApp

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.MediECareApp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

// Login Page Class:
class LoginActivity : AppCompatActivity()
{
    // Binding:
    private lateinit var binding : ActivityLoginBinding

    // ActionBar:
    private lateinit var actionBar: ActionBar

    // ProgressDialog (Old code - Deprecated):
    private lateinit var progressDialog : ProgressDialog

    // Firebase Authentication:
    private lateinit var firebaseAuth : FirebaseAuth

    // Data Variables:
    private var email = ""
    private var password = ""

    override fun onCreate (savedInstanceState: Bundle?)
    {
        super.onCreate (savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ProgressDialog (Old Code - Deprecated):
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait...")
        progressDialog.setMessage("Logging in...")
        progressDialog.setCanceledOnTouchOutside(false)

        actionBar = supportActionBar!!
        actionBar.title = "THONL"

        // Firebase Authentication:
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        // Forgot Password Linker:
        binding.ForgotPasswordTextView.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        var sharedPreferences = getSharedPreferences("userPref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("password", password)
        editor.apply()

        val password = sharedPreferences.getString("password", "")
        if (password?.isNotEmpty()!!)
        {
            firebaseLogin()
        }

        // Handles the clicking and un-clicking of the Remember Password button:
        binding.RememberPasswordRadioButton.setOnClickListener(View.OnClickListener {
            if (!(binding.RememberPasswordRadioButton.isSelected))
            {
                binding.RememberPasswordRadioButton.isChecked = true
                binding.RememberPasswordRadioButton.isSelected = true
            }
            else
            {
                binding.RememberPasswordRadioButton.isChecked = false
                binding.RememberPasswordRadioButton.isSelected = false
            }
        })

        // This handles the Log in button:
        binding.LoginButton.setOnClickListener {
            validateData()
        }

        // This handles the About Us Linker:
        binding.AboutUsTextView.setOnClickListener {
            startActivity(Intent(this, AboutUsActivity::class.java))
        }

            // This handles the Register button:
            val registerButton : Button = findViewById(R.id.RegisterButton)
        registerButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    // Constraint Checker for Email And Password:
    private fun validateData()
    {
        email = binding.EmailEditText.text.toString().trim()
        password = binding.passwordEditText.text.toString().trim()

        if (TextUtils.isEmpty(email))
        {
            binding.EmailEditText.error = "You can not leave the email field blank, please retry again!"
        }
        else if (TextUtils.isEmpty(password))
        {
            binding.passwordEditText.error = "You can not leave the password field blank, please retry again!"
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
        else if ((!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) && (Patterns.EMAIL_ADDRESS.matcher(email).matches()))
        {
            firebaseLogin()
        }
    }

    // Handles the log in for Firebase:
    private fun firebaseLogin()
    {
        // Logical Recursive step of the progress:
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)

            // If you successfully log in, then:
            .addOnSuccessListener {
                progressDialog.dismiss()

                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Logged in as $email", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, HomePageActivity::class.java))
                finish()
            }

            // If you don't successfully log in, then:
            .addOnFailureListener { e->
                progressDialog.dismiss()
                Toast.makeText(this, " ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    // Authentication Function for User:
    private fun checkUser()
    {
        // Redirects the User to the Profile Activity page if that user is logged in:
        val firebaseUser = firebaseAuth.currentUser

        if (firebaseUser != null)
        {
            startActivity(Intent(this, HomePageActivity::class.java))
            finish()
        }
    }
}