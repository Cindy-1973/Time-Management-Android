package com.example.myapplication.viewmodel

import com.example.myapplication.data.entity.Task

data class TasksUIState(
    val taskList: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val operationMessage: String? = null,
//    val class1: List<Task> = emptyList(),
//    val class2: List<Task> = emptyList(),
//    val class3: List<Task> = emptyList(),
//    val class4: List<Task> = emptyList()
)