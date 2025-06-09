package com.example.myapplication.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val isImportant: String,
    val isUrgent: String,
    val classification: String,
    val location: String,
    val startDate: String,
    val dueDate: String,
    val description: String
)
