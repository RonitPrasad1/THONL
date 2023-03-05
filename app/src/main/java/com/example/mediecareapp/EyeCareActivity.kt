// Imports/Packages:
package com.example.mediecareapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.MediECareApp.AppointmentPageActivity
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.activity.result.contract.ActivityResultContracts

@Preview(showBackground = true)
@Composable
fun EyeCareActivityPreview()
{
    DoctorCards()
    CenteredTitle("Eye Care Assistance")
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
fun CenteredTitle(title: String)
{
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "Eye Care Assistance",
            fontWeight = FontWeight.Bold,
            fontSize = 18.5.sp,
            modifier = Modifier
                .height(112.dp) // adjust the value as needed to scale vertically
                .wrapContentHeight()
        )
    }
}

@Composable
fun EyeCareScreen()
{
    Scaffold(
        topBar =
        {
            TopAppBar(
                title = { Text("") },
                backgroundColor = Color.Black
            )
        },
        content = { DoctorCards() }
    )
}

@Composable
fun DoctorCards()
{
    val context = LocalContext.current

    val namesPlusQualifications = listOf(
        "Dr. Nguyen Ophthalmologist",
        "Dr. Smithson Ophthalmologist ",
        "Dr. Jakeb Ophthalmologist",
        "Dr. Rajesh Ophthalmologist",
        "Dr. Koothrapali Ophthalmologist",
        "Dr. Alexandra Ophthalmologist"
    )

    val shortDescriptionOfEachDoctors = listOf(
        "An highly qualified and experienced ophthalmologist that has multiple decades of experience.",
        "An highly skilled and experienced ophthalmologist with a passion for helping his patients.",
        "Jakeb's patients appreciate his friendly and patient approach to helping them with their issues at hand.",
        "He is passionate about utilizing the latest technology to provide his patients with the best possible outcomes.",
        "Koothrapali is known for his exceptional skills in treating patients with basic and complex eye conditions.",
        "Has a special interest in glaucoma and has helped countless patients manage this condition."
    )

    // Create a list of Boolean values to track the expanded state of each card
    val expandedStates = remember {
        mutableStateListOf<Boolean>().apply { repeat(namesPlusQualifications.size) { add(false) } }
    }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // No need to do anything here
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 85.dp)
    ) {
        namesPlusQualifications.chunked(2).forEach { chunk ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                chunk.forEachIndexed { index, title ->
                    val cardIndex = namesPlusQualifications.indexOf(title)

                    Card(
                        modifier = Modifier
                            .size(180.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color.White)
                            .clickable { expandedStates[cardIndex] = !expandedStates[cardIndex] },
                        elevation = 4.dp,
                        shape = RoundedCornerShape(16.dp),
                        contentColor = MaterialTheme.colors.onSurface,
                        border = BorderStroke(1.dp, Color(0xFF2045FC))
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .animateContentSize()
                                .padding(4.dp, 50.dp, 2.dp, 8.dp),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
//                            Text(
//                                text = if (expandedStates[cardIndex]) "" else title,
//                                textAlign = TextAlign.Center,
//                                style = MaterialTheme.typography.h6.copy(fontSize = 15.5.sp)
//                            )

                            if (expandedStates[cardIndex])
                            {
                                Text(
                                    text = shortDescriptionOfEachDoctors[cardIndex],
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.body2.copy(fontSize = 11.4.sp),
                                )

                                Button(
                                        onClick = {
                                            val intent = Intent(context, AppointmentPageActivity::class.java)
                                            launcher.launch(intent)
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
                                        fontSize = 11.4.sp,
                                        style = MaterialTheme.typography.body1
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}