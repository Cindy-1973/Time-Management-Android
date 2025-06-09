package com.example.myapplication.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun TimerScreen(modifier: Modifier = Modifier) {
    var timeLeft by remember { mutableStateOf(25 * 60) } // 25分钟秒数
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        while (isRunning && timeLeft > 0) {
            delay(1000L)
            timeLeft -= 1
        }
        if (timeLeft == 0) {
            isRunning = false
            // 可以加提示音或弹窗
        }
    }

    val minutes = timeLeft / 60
    val seconds = timeLeft % 60

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "%02d:%02d".format(minutes, seconds), style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Button(
                onClick = { isRunning = true },
                enabled = !isRunning
            ) {
                Text("Start")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    isRunning = false
                    timeLeft = 25 * 60
                }
            ) {
                Text("Reset")
            }
        }
    }
}
