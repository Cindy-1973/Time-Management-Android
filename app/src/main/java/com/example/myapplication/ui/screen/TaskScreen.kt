package com.example.myapplication.ui.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.myapplication.viewmodel.TaskViewModel
import com.example.myapplication.data.entity.Task
import com.example.myapplication.ui.utility.RadioButtonSingleSelection
import com.example.myapplication.ui.utility.DatePicker

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskScreen(
    viewModel: TaskViewModel = koinViewModel(),
    onTaskCreated: (Int) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isImportant by remember { mutableStateOf("") }
    var isUrgent by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }

    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.operationMessage) {
        uiState.operationMessage?.let { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            viewModel.clearMessage()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Add New Task") })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title:") },
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                Column {
                    Text(
                        text = "Is this task important?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.fillMaxWidth()
                    )
                    RadioButtonSingleSelection(
                        radioOptions = listOf("Yes", "No"),
                        onOptionSelected = { selected -> isImportant = selected })
                }
            }

            item {
                Column {
                    Text(
                        text = "Is this task urgent?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.fillMaxWidth()
                    )
                    RadioButtonSingleSelection(
                        radioOptions = listOf("Yes", "No"),
                        onOptionSelected = { selected -> isUrgent = selected })
                }
            }

            item {
                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },
                    label = { Text("Location") },
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                DatePicker("Start Date:") { millis ->
                    startDate = millis.toString()
                }
            }

            item {
                DatePicker("Due Date:") { millis ->
                    dueDate = millis.toString()
                }
            }

            item {
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    maxLines = 5,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                Button(
                    onClick = {
                        if (title.isBlank()) {
                            Toast.makeText(context, "Title cannot be empty", Toast.LENGTH_SHORT).show()
                            return@Button
                        }
                        val task = Task(
                            title = title,
                            isImportant = isImportant,
                            isUrgent = isUrgent,
                            location = location,
                            startDate = startDate,
                            dueDate = dueDate,
                            description = description
                        )
                        viewModel.addNewTask(task)
                        title = ""
                        isImportant = ""
                        isUrgent = ""
                        location = ""
                        startDate = ""
                        dueDate = ""
                        description = ""
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Submit")
                }
            }
        }
    }
}
