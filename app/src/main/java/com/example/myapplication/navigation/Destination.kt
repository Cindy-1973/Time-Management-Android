package com.example.myapplication.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.ui.graphics.vector.ImageVector


enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    LIST("list", "List", Icons.Default.Home, "List"),
    TASK("task", "Task", Icons.Default.Add, "Task"),
    TIMER("timer", "Timer", Icons.Default.Lock, "Timer"),
    ME("me", "Me", Icons.Default.AccountCircle, "Me")
}
