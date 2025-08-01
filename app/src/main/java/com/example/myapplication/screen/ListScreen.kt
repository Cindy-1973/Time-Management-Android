package com.example.myapplication.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.AppDatabase
import com.example.myapplication.entity.Task
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
import com.example.myapplication.ui.theme.CardColorList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(modifier: Modifier = Modifier, onEdit: (Any) -> Unit) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val taskDao = db.TaskDao()

    val coroutineScope = rememberCoroutineScope()
    var tasks by remember { mutableStateOf<List<Task>>(emptyList()) }

    LaunchedEffect(Unit) {
        tasks = withContext(Dispatchers.IO) {
            taskDao.getAllTasks()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Task List") })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tasks, key = { it.id }) { task ->
                val cardColor = remember(task.id) { CardColorList.random() }
//                val visibilityState = remember { MutableTransitionState(true) }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = cardColor
                    ),
                    onClick = { onEdit(task.id) }
                ) {
                    Box {
                        IconButton(
                            onClick = {
                                coroutineScope.launch(Dispatchers.IO) {
                                    taskDao.deleteTask(task)
                                    val updated = taskDao.getAllTasks()
                                    withContext(Dispatchers.Main) {
                                        tasks = updated
                                    }
                                }
                            },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size(24.dp)
                                .padding(5.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Delete",
                                tint = Color.Black
                            )
                        }

                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = task.isFinished,
                                    onCheckedChange = { newValue ->
                                        coroutineScope.launch(Dispatchers.IO) {
                                            val updatedTask = task.copy(isFinished = newValue)
                                            taskDao.updateTask(updatedTask)
                                            withContext(Dispatchers.Main) {
                                                tasks = tasks.map {
                                                    if (it.id == task.id) updatedTask else it
                                                }
                                            }
                                        }
                                    }
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    text = task.title,
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun convertMillisToDate(millis: String?): String {
    if (millis.isNullOrBlank()) return ""
    return try {
        val timeInMillis = millis.toLong()
        val date = Date(timeInMillis)
        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        val formattedDate = formatter.format(date)
        println("convertMillisToDate: input=$millis, output=$formattedDate")
        formattedDate
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}
