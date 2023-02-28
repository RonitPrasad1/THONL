package com.example.mediecareapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun EyeCareActivityPreview()
{
    doctorCards ()
}
class EyeCareActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContent {
            EyeCareScreen()
        }
    }
}

@Composable
fun EyeCareScreen()
{
    Scaffold(
        topBar =
        {
            TopAppBar(
                title = { Text("Eye Care Page") },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        content = { doctorCards () }
    )
}

@Composable
fun doctorCards() {
    val titles = listOf (
        "Dr. Nguyen Ophthalmologist",
        "Dr. Smithson Ophthalmologist ",
        "Dr. Something Qualifications",
        "Dr. Something Qualifications",
        "Dr. Something Qualifications",
        "Dr. Something Qualifications"
    )
    var expandedState by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 140.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        for (i in 0 until 6)
        {
            Card(
                modifier = Modifier
                    .size(177.5.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color.White)
                    .clickable { expandedState = !expandedState },
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
                contentColor = MaterialTheme.colors.onSurface,
                border = BorderStroke(1.dp, Color(0xFF2045FC))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .animateContentSize()
                        .padding(4.dp, 70.dp, 4.dp, 4.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if(expandedState) "" else titles[i],
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h6.copy(fontSize = 15.5.sp)
                    )
                    if (expandedState)
                    {
                        Button(
                            onClick =
                            {
                                // This handles the Appointment Button:
//                                binding.AboutUsTextView.setOnClickListener {
//                                    startActivity(Intent(this, AppointmentPage::class.java))
//                                }
                            },
                            modifier = Modifier
                                .padding(top = 1.dp, bottom = 1.dp)
                                .fillMaxWidth()
                                .height(60.dp)
                                .padding(14.dp)
                                .background(Color(0xFF2045FC), RoundedCornerShape(30.dp)),

                            shape = RoundedCornerShape(28),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFF2045FC),
                            )
                        ) {
                            Text(
                                "Book Appointment",
                                color = Color.White,
                                fontSize = 10.sp,
                                style = MaterialTheme.typography.body1
                            )
                        }
                    }
                }
            }
        }
    }
}