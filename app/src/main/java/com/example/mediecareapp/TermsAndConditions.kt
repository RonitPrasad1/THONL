package com.example.MediECareApp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.example.MediECareApp.databinding.ActivityTermsAndConditionsBinding

class TermsAndConditions : AppCompatActivity()
{
    //Binding:
    private lateinit var binding : ActivityTermsAndConditionsBinding

    //ActionBar:
    private lateinit var actionBar: ActionBar

    //Message:
    private val termsAndConditions = "By agreeing to the terms and conditions, you are therefore agreeing to be truthful in the sense that, you are indeed in need of medical assistance. The medical services that are provided by this medical application are used by patients and doctors to prevent, diagnose, and treat an illness/medical condition.\n" +
            "In the case that you are found guilty of NOT having an injury, disease, or any sort of medical condition and you the patient are doing this for your own benefit, then you will be prosecuted at the highest-level possible by the Department of Justice and undergo review. \n" +
            "Besides breaking laws, you can find yourself go through a sequences of problems regarding your medical health. With this, we highly encourage you to act responsibly and use this medical application for valid reasons."

    //Function to handle binding:
    override fun onCreate (savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsAndConditionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title = "Terms and Conditions"
    }

    fun showTermsAndConditions()
    {
        // Create a new dialog window
        val dialog = Dialog(this)

        dialog.setTitle("Terms and Conditions")
        dialog.setContentView(R.layout.activity_terms_and_conditions)

        val textView = dialog.findViewById(R.id.TermsAndConditionsButton) as TextView
        textView.text = termsAndConditions

        dialog.show()
    }
}
