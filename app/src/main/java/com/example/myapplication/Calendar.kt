package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen() {
    val today = remember { LocalDate.now() }
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    val selectedDate = remember { mutableStateOf<LocalDate?>(null) }

    val daysInMonth = currentMonth.lengthOfMonth()
    val firstDayOfWeek = currentMonth.atDay(1).dayOfWeek.value % 7 // Sunday = 0

    val events = remember {
        listOf(3, 7, 12, 18, 22).map { currentMonth.atDay(it) }.toSet()
    }

    val eventList = listOf(
        "Meeting with team",
        "Project deadline",
        "Doctor appointment",
        "Friend's birthday",
        "Gym session",
        "Meeting with team",
        "Project deadline",
        "Doctor appointment",
        "Friend's birthday",
        "Gym session",
        "Meeting with team",
        "Project deadline",
        "Doctor appointment",
        "Friend's birthday",
        "Gym session"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFE3F2FD))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { currentMonth = currentMonth.minusMonths(1) }) {
                Text("<")
            }
            Text(
                text = "${currentMonth.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)} ${currentMonth.year}",
                fontSize = 24.sp
            )
            Button(onClick = { currentMonth = currentMonth.plusMonths(1) }) {
                Text(">")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat").forEach {
                Text(
                    text = it,
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1565C0)
                )
            }
        }

        val totalCells = firstDayOfWeek + daysInMonth
        val rows = (totalCells + 6) / 7

        Column(modifier = Modifier.weight(1f)) {
            var dayCounter = 1
            for (row in 0 until rows) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    for (col in 0..6) {
                        val cellIndex = row * 7 + col
                        if (cellIndex < firstDayOfWeek || dayCounter > daysInMonth) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .padding(2.dp)
                            ) {}
                        } else {
                            val thisDate = currentMonth.atDay(dayCounter)
                            val isEvent = thisDate in events
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .padding(2.dp)
                                    .border(1.dp, Color(0xFF90CAF9))
                                    .background(
                                        when {
                                            thisDate == selectedDate.value -> Color(0xFF64B5F6)
                                            isEvent -> Color(0xFFBBDEFB)
                                            else -> Color.Transparent
                                        }
                                    )
                                    .clickable {
                                        selectedDate.value = thisDate
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = dayCounter.toString(),
                                    color = if (thisDate == today) Color(0xFF1565C0) else Color.Black
                                )
                            }
                            dayCounter++
                        }
                    }
                }
            }
        }

        Text(
            text = "Event List",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1565C0)
        )

        Column(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
            eventList.forEach { event ->
                Text(
                    text = "- $event",
                    fontSize = 14.sp,
                    color = Color(0x00000),
                    modifier = Modifier.padding(2.dp)
                )
            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    MyApplicationTheme {
        CalendarScreen()
    }
}
