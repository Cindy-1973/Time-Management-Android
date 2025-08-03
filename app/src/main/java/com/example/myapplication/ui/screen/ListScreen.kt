package com.example.myapplication.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import com.example.myapplication.viewmodel.TaskViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun ListScreen(
    viewModel: TaskViewModel = koinViewModel(),
    onTaskSelected: (Int) -> Unit,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Task List") })
        }
    ) {paddingValues->
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(uiState.taskList, key = { it.id }) { task ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = task.isFinished,
                        onCheckedChange = { newValue ->
                            val updated = task.copy(isFinished = newValue)
                            viewModel.updateTask(updated)
                        }
                    )
                    Spacer(Modifier.width(8.dp))
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onTaskSelected(task.id) }
                    ) {
                        Text(text = task.title, style = MaterialTheme.typography.titleMedium)
                        if (task.isUrgent == "Yes" && task.isImportant == "No") {
                            Text(text = "Urgent but not important", style = MaterialTheme.typography.bodySmall)
                        }
                        else  if (task.isUrgent == "Yes" && task.isImportant == "Yes") {
                            Text(text = "Urgent and important", style = MaterialTheme.typography.bodySmall)
                        }
                        else  if (task.isUrgent == "No" && task.isImportant == "Yes") {
                            Text(text = "Important but not urgent", style = MaterialTheme.typography.bodySmall)
                        }
                        else  if (task.isUrgent == "No" && task.isImportant == "No") {
                            Text(text = "Not important and urgent", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                    IconButton(onClick = {
                        viewModel.deleteTask(task)
                    }) {
                        Icon(Icons.Default.Close, contentDescription = "Delete")
                    }
                }
            }
        }
        }
    }
}
