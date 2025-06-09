package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.core.view.WindowInsetsControllerCompat
import com.example.myapplication.navigation.AppNavigationBar

import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
        setContent {
            MyApplicationTheme {
                AppNavigationBar(modifier = Modifier)
            }
        }
    }
}

