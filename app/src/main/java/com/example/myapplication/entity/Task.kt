package com.example.myapplication.entity

import android.content.IntentSender.OnFinished
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["title"],
    unique = true)])

data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val isImportant: String = "yes",
    val isUrgent: String = "yes",
    val location: String,
    val startDate: String,
    val dueDate: String,
    val description: String,
    val isFinished: Boolean = false
)
