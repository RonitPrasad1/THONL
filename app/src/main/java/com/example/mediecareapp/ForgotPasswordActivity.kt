package com.example.MediECareApp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import com.example.MediECareApp.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity()
{
    //Binding:
    private lateinit var binding : ActivityForgotPasswordBinding

    private lateinit var progressDialog: ProgressDialog

    //Data Variable:
    private var currentEmail = ""

    override fun onCreate (savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //This handles the Log in button:
        binding.ConfirmButton.setOnClickListener {
            validateForgotEmailChecker()
        }

        //This redirects us back to the login page once you've done the "forgot password" check:
        binding.LoginTextView.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    /*
        //Java Code for sending a Password Reset email:

        FirebaseAuth.getInstance().sendPasswordResetEmail("user@example.com")
        .addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if (task.isSuccessful())
                {
                    Log.d(TAG, "Password reset email has been sent.");
                }
            }
        });
     */

    //Validates the data for the Email:
    private fun validateForgotEmailChecker()
    {
        currentEmail = binding.EditEmailText.text.toString().trim()

        if (TextUtils.isEmpty(currentEmail))
        {
            binding.EditEmailText.error = "You can not leave the email field blank, please retry again!"
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(currentEmail).matches())
        {
            binding.EditEmailText.error = "Invalid Email Formatting, please use a proper format!"
        }
    }
}