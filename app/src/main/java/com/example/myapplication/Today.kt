package com.example.myapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

@SuppressLint("InvalidColorHexValue")
@Composable
fun TodayScreen() {
    // 配色方案
    val backgroundColor = Color(0xFFE3F2FD)       // 整体背景
    val headerBackground = Color(0xFF1565C0)      // 顶部标题栏
    val headerTextColor = Color.White             // 顶部标题文字
    val labelBackground = Color(0xFFBBDEFB)       // 表格表头格子背景
    val labelTextColor = Color(0xFF0D47A1)        // 文本颜色
    val borderColor = Color(0xFF90CAF9)           // 边框颜色

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        val cellTitleWidth = maxWidth * 0.2f
        val cellTitleHeight = maxHeight * 0.05f
        val cellWidth = maxWidth * 0.4f
        val cellHeight = maxHeight * 0.45f

        Column(modifier = Modifier.background(backgroundColor)) {
            Box(
                modifier = Modifier
                    .background(headerBackground)
                    .fillMaxWidth()
                    .height(48.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Today List",
                    color = headerTextColor,
                    fontSize = 30.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier
                .height(cellTitleHeight)
                .border(1.dp, borderColor)) {
                Box(
                    modifier = Modifier
                        .width(cellTitleWidth)
                        .height(cellTitleHeight),
                    contentAlignment = Alignment.Center
                ) {}
                Box(
                    modifier = Modifier
                        .width(cellWidth)
                        .height(cellTitleHeight)
                        .background(labelBackground)
                        .border(1.dp, borderColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Urgent",
                        color = labelTextColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp)
                }
                Box(
                    modifier = Modifier
                        .width(cellWidth)
                        .height(cellTitleHeight)
                        .background(labelBackground)
                        .border(1.dp, borderColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Not Urgent",
                        color = labelTextColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp)
                }
            }

            Row(modifier = Modifier.height(cellHeight)) {
                Box(
                    modifier = Modifier
                        .width(cellTitleWidth)
                        .height(cellHeight)
                        .border(1.dp, borderColor)
                        .background(labelBackground),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Important",
                        color = labelTextColor,
                        modifier = Modifier.rotate(270f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp)
                }
                Box(
                    modifier = Modifier
                        .width(cellWidth)
                        .height(cellHeight)
                        .border(1.dp, borderColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Demand:",
                        color = labelTextColor)
                }
                Box(
                    modifier = Modifier
                        .width(cellWidth)
                        .height(cellHeight)
                        .border(1.dp, borderColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Zone:",
                        color = labelTextColor)
                }
            }

            Row(modifier = Modifier.height(cellHeight)) {
                Box(
                    modifier = Modifier
                        .width(cellTitleWidth)
                        .height(cellHeight)
                        .border(1.dp, borderColor)
                        .background(labelBackground),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Not Important",
                        color = labelTextColor,
                        modifier = Modifier.rotate(270f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp)
                }
                Box(
                    modifier = Modifier
                        .width(cellWidth)
                        .height(cellHeight)
                        .border(1.dp, borderColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Illusion:",
                        color = labelTextColor)
                }
                Box(
                    modifier = Modifier
                        .width(cellWidth)
                        .height(cellHeight)
                        .border(1.dp, borderColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Escape:",
                        color = labelTextColor)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodayScreenPreview() {
    MyApplicationTheme {
        TodayScreen()
    }
}