package com.example.myapplication.ui.screen

import androidx.compose.runtime.Composable
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel
import com.example.myapplication.viewmodel.TaskViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.platform.LocalContext
import com.example.myapplication.data.entity.Task
import com.example.myapplication.ui.utility.RadioButtonSingleSelection

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskDetailScreen(
    taskId: Int,
    onBack: () -> Unit,
    viewModel: TaskViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    // 监听 message
    LaunchedEffect(uiState.operationMessage) {
        uiState.operationMessage?.let { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            viewModel.clearMessage()
        }
    }

    // 找到 task
    val original = uiState.taskList.firstOrNull { it.id == taskId }

    // 编辑状态（复制原始，避免直接改 list）
    var title by remember { mutableStateOf(original?.title ?: "") }
    var description by remember { mutableStateOf(original?.description ?: "") }
    var location by remember { mutableStateOf(original?.location ?: "") }
    var startDate by remember { mutableStateOf(original?.startDate ?: "") }
    var dueDate by remember { mutableStateOf(original?.dueDate ?: "") }
    var isImportant by remember { mutableStateOf(original?.isImportant ?: "") }
    var isUrgent by remember { mutableStateOf(original?.isUrgent ?: "") }
    var isFinished by remember { mutableStateOf(original?.isFinished ?: false) }

    if (original == null) {
        // 任务不存在
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Task Detail") },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Task not found", fontWeight = FontWeight.Bold)
            }
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Task Detail") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
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
                com.example.myapplication.ui.utility.DatePicker("Start Date:") { millis ->
                    startDate = millis.toString()
                }
            }

            item {
                com.example.myapplication.ui.utility.DatePicker("Due Date:") { millis ->
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isFinished,
                        onCheckedChange = { isFinished = it }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Finished")
                }
            }

            item {
                Button(
                    onClick = {
                        if (title.isBlank()) {
                            Toast.makeText(context, "Title cannot be empty", Toast.LENGTH_SHORT).show()
                            return@Button
                        }
                        val updatedTask = Task(
                            id = original!!.id, // 保留原任务id
                            title = title,
                            isImportant = isImportant,
                            isUrgent = isUrgent,
                            location = location,
                            startDate = startDate,
                            dueDate = dueDate,
                            description = description,
                            isFinished = isFinished
                        )
                        viewModel.updateTask(updatedTask)
                        onBack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Save")
                }
            }
        }
    }
}
