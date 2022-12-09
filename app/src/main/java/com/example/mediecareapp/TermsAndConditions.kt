package com.example.MediECareApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.MediECareApp.databinding.ActivityTermsAndConditionsBinding

class TermsAndConditions : AppCompatActivity()
{
    //Binding:
    private lateinit var binding : ActivityTermsAndConditionsBinding

    //Function to handle binding:
    override fun onCreate (savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsAndConditionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
