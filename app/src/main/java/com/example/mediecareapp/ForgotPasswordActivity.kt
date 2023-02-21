// Imports/Packages:
package com.example.MediECareApp

import com.example.MediECareApp.retrofit.RetroFitClient
import com.example.MediECareApp.retrofit.ApiService

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.MediECareApp.databinding.ActivityForgotPasswordBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

    // For Forget Password Feature:
    private val retrofitClient = RetroFitClient.getClient()

    // Handles the API Back-end work:
    val apiService = retrofitClient.create(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title = "THONL"

        // This handles the Log in button:
        binding.ConfirmButton.setOnClickListener {
            validateForgotEmailChecker()
        }

        // This redirects us back to the login page once you've done the "forgot password" check:
        binding.LoginTextView.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    // Validates the data for the Email:
    private fun validateForgotEmailChecker() {
        currentEmail = binding.EditEmailText.text.toString().trim()

        if (TextUtils.isEmpty(currentEmail)) {
            binding.EditEmailText.error =
                "You can not leave the email field blank, please retry again!"
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(currentEmail).matches())
        {
            binding.EditEmailText.error = "Invalid Email Formatting, please use a proper format!"
        }
    }

    // Error (Syntax & Logic): Feb 20th | 10:54pm
    private fun sendResetEmail() {
        val email = binding.EditEmailText.text.toString().trim()
        if (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            //Show progress dialog
            progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Sending password reset email...")
            progressDialog.show()

            //Make network request to send password reset email:
            val apiService = RetroFitClient.getClient().create(ApiService::class.java)
            apiService.sendPasswordResetEmail(email)
                .enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call <Void>, response: Response<Void>) {

                        //Hide progress dialog:
                        progressDialog.dismiss()

                        //Check if request was successful:
                        if (response.isSuccessful)
                        {
                            //Display success message:
                            Toast.makeText(
                                this@ForgotPasswordActivity,
                                "Password reset email sent!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        else
                        {
                            //Display error message:
                            Toast.makeText(
                                this@ForgotPasswordActivity,
                                "Failed to send password reset email.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        //Display progress dialog:
                        progressDialog.dismiss()

                        //Display error message:
                        Toast.makeText(
                            this@ForgotPasswordActivity,
                            "Failed to send password reset email.",
                            Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}