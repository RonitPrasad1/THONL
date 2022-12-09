package com.example.MediECareApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.MediECareApp.databinding.ActivityAboutUsBinding

class AboutUsActivity : AppCompatActivity()
{
    //Binding:
    private lateinit var binding : ActivityAboutUsBinding

    //Function to handle binding:
    override fun onCreate (savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}