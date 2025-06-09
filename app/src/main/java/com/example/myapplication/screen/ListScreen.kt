package com.example.myapplication.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.example.myapplication.AppDatabase
import com.example.myapplication.entity.Task
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val taskDao = db.TaskDao()

    val coroutineScope = rememberCoroutineScope()

    var tasks by remember { mutableStateOf<List<Task>>(emptyList()) }

    LaunchedEffect(Unit) {
        tasks = taskDao.getAllTasks()
    }

    fun reloadTasks() {
        coroutineScope.launch {
            tasks = taskDao.getAllTasks()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Task List") })
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tasks, key = { it.id }) { task ->

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Title: ${task.title}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(text = "Important: ${task.isImportant}")
                        Text(text = "Urgent: ${task.isUrgent}")
                        Text(text = "Classification: ${task.classification}")
                        Text(text = "Location: ${task.location}")
                        Text(text = "Start Date: ${convertSecondsToDate(task.startDate)}")
                        Text(text = "Due Date: ${convertSecondsToDate(task.dueDate)}")
                        Text(text = "Description: ${task.description}")

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    taskDao.deleteTask(task)
                                    reloadTasks()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                        ) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}
fun convertSecondsToDate(seconds: String?): String {
    if (seconds.isNullOrBlank()) return ""
    return try {
        val secs = seconds.toLong()
        val millis = secs * 1000
        val date = Date(millis)
        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        val formattedDate = formatter.format(date)
        println("convertSecondsToDate: input=$seconds, output=$formattedDate")
        formattedDate
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}
