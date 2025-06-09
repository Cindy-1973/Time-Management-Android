package com.example.myapplication


import android.os.CountDownTimer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun CountdownScreen() {
    var timeLeft by remember { mutableStateOf(60) } // 60秒倒计时
    var isRunning by remember { mutableStateOf(false) }

    var timer: CountDownTimer? by remember { mutableStateOf(null) }

    fun startTimer() {
        if (isRunning) return
        isRunning = true
        timer = object : CountDownTimer((timeLeft * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = (millisUntilFinished / 1000).toInt()
            }

            override fun onFinish() {
                isRunning = false
                timeLeft = 0
            }
        }.start()
    }

    fun resetTimer() {
        timer?.cancel()
        timeLeft = 30
        isRunning = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "$timeLeft min",
            fontSize = 48.sp,
            color = Color(0xFF0D47A1)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Button(onClick = { startTimer() }) {
                Text("Start")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { resetTimer() }) {
                Text("Reset")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountdownScreenPreview() {
    MyApplicationTheme {
        CountdownScreen()
    }
}