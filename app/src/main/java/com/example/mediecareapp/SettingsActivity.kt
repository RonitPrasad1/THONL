package com.example.MediECareApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.MediECareApp.databinding.ActivitySettingsBinding
import com.google.firebase.auth.FirebaseAuth

class SettingsActivity : AppCompatActivity()
{
    //Binding:
    private lateinit var binding : ActivitySettingsBinding

    //Firebase Authentication
    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate (savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Handles the AboutUs Link
        binding.AboutUsButton.setOnClickListener {
            startActivity(Intent(this, AboutUsActivity::class.java))
        }

        //Handles the Terms And Conditions Link
        binding.TermsAndConditionsButton.setOnClickListener {
            startActivity(Intent(this, TermsAndConditions::class.java))
        }

        firebaseAuth = FirebaseAuth.getInstance()

        binding.SettingsLogOutButton.setOnClickListener {

            firebaseAuth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}