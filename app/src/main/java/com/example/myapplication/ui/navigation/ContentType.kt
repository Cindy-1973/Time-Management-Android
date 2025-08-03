package com.example.myapplication.ui.navigation

sealed interface ContentType {
    object List : ContentType
    object ListAndDetail : ContentType
}