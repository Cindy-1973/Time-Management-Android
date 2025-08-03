package com.example.myapplication.ui.utility


import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable

@Composable
fun CheckBoxTool(
    checked: Boolean,
    onCheckChange: (Boolean) -> Unit
) {
    Checkbox(
        checked = checked,
        onCheckedChange = onCheckChange,
    )
}