package com.example.MediECareApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.MediECareApp.databinding.ActivityAboutUsBinding

class AboutUsActivity : AppCompatActivity()
{
    //Binding:
    private lateinit var binding : ActivityAboutUsBinding

    //ActionBar:
    private lateinit var actionBar: ActionBar

    //Function to handle binding:
    override fun onCreate (savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title = "About Us"

        //This handles the Return Back feature in the About Us Page:
        binding.ReturnBackTextView.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }
}