package com.example.MediECareApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.MediECareApp.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity()
{
    //Binding:
    private lateinit var binding : ActivityProfileBinding

    //ActionBar:
    private lateinit var actionBar : ActionBar

    //Firebase Authentication
    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!

        //Firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.LogOutButton.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }

        binding.HomePageButton.setOnClickListener {
            startActivity (Intent(this, HomePageActivity::class.java))
        }

    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser

        if (firebaseUser != null)
        {
            //Currently in EditView
            val email = firebaseUser.email

            //~ Swapping to TextView ~

            //TextView:
            binding.EmailTextView.text = email
        }
        else
        {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}