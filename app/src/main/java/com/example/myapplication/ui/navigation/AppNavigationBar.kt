package com.example.myapplication.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person

@Composable
fun AppNavigationBar(
    currentRoute: String?,
    onNavigate: (Screens) -> Unit
) {
    val items = listOf(
        Screens.ListScreen to Icons.Default.List,
        Screens.TaskScreen to Icons.Default.Add,
        Screens.TimerScreen to Icons.Default.Lock,
        Screens.MeScreen to Icons.Default.Person
    )
    NavigationBar {
        items.forEach { (screen, icon) ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = { onNavigate(screen) },
                icon = {
                    Icon(imageVector = icon, contentDescription = screen.route)
                }
            )
        }
    }
}