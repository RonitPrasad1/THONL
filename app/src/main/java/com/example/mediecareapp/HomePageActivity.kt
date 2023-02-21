// Imports/Packages:
package com.example.MediECareApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.MediECareApp.databinding.ActivityHomePageBinding

// Homepage Class:
class HomePageActivity : AppCompatActivity()
{
    // Binding:
    private lateinit var binding : ActivityHomePageBinding

    // ActionBar:
    private lateinit var actionBar: ActionBar

    override fun onCreate (savedInstanceState: Bundle?)
    {
        super.onCreate (savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handles the "Settings" button:
        binding.SettingImageButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        actionBar = supportActionBar!!
        actionBar.title = "Home Page"
    }
}