// Imports/Packages:
package com.example.MediECareApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.MediECareApp.databinding.ActivitySettingsBinding
import com.google.firebase.auth.FirebaseAuth

// Settings Page Class:
class SettingsActivity : AppCompatActivity()
{
    // Binding:
    private lateinit var binding : ActivitySettingsBinding

    // ActionBar:
    private lateinit var actionBar: ActionBar

    // Firebase Authentication
    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate (savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title = "Settings"

        //Handles the Terms And Conditions Link:
        binding.TermsAndConditionsButton.setOnClickListener {
            startActivity(Intent(this, TermsAndConditions::class.java))
        }

        // Disclaimer Button:
//        binding.DisclaimerButton.setOnClickListener {
//            startActivity(Intent(this, LoginActivity::class.java))
//        }s

        binding.ReturnBackTextView.setOnClickListener {
            startActivity(Intent(this, HomePageActivity::class.java))
        }

        firebaseAuth = FirebaseAuth.getInstance()

        binding.SettingsLogOutButton.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}