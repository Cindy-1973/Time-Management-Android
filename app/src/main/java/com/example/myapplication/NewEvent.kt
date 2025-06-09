package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun AddEventScreen() {
    val backgroundColor = Color(0xFFE3F2FD)       // 淡蓝色背景
    val titleColor = Color(0xFF0D47A1)            // 深蓝标题
    val labelColor = Color(0xFF1565C0)            // 标签文字蓝
    val fieldBgColor = Color(0xFFFFFFFF)          // 输入框白底
    val buttonColor = Color(0xFF42A5F5)           // 提交按钮蓝色

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Add New Event",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = titleColor
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Event Name", color = labelColor) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = fieldBgColor,
                focusedContainerColor = fieldBgColor
            )
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Description", color = labelColor) },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 4,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = fieldBgColor,
                focusedContainerColor = fieldBgColor
            )
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Date", color = labelColor) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = fieldBgColor,
                focusedContainerColor = fieldBgColor
            )
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Tag", color = labelColor) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = fieldBgColor,
                focusedContainerColor = fieldBgColor
            )
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Priority", color = labelColor) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = fieldBgColor,
                focusedContainerColor = fieldBgColor
            )
        )

        Button(
            onClick = { /* 提交事件 */ },
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Submit", color = Color.White, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddEventScreenPreview() {
    MyApplicationTheme {
        AddEventScreen()
    }
}
