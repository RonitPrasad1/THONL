// Imports/Packages:
package com.example.MediECareApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.MediECareApp.databinding.ActivityAppointmentPageBinding

// Homepage Class:
class AppointmentPageActivity : AppCompatActivity()
{
    // Binding:
    private lateinit var binding : ActivityAppointmentPageBinding

    // ActionBar:
    private lateinit var actionBar: ActionBar

    override fun onCreate (savedInstanceState: Bundle?)
    {
        super.onCreate (savedInstanceState)
        binding = ActivityAppointmentPageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        actionBar = supportActionBar!!
        actionBar.title = "Appointment Page"
    }
}