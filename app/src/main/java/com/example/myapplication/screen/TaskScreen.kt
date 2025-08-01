package com.example.myapplication.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.AppDatabase
import com.example.myapplication.utility.DatePicker
import com.example.myapplication.utility.RadioButtonSingleSelection
import com.example.myapplication.entity.Task
import com.example.myapplication.dao.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskScreen(modifier: Modifier = Modifier,
               id: Int,
               onDone: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isImportant by remember { mutableStateOf("") }
    var isUrgent by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }

    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val taskDao = db.TaskDao()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(id) {
        val task = withContext(Dispatchers.IO) {
            taskDao.getTaskById(id)
        }
        task?.let {
            title = it.title
            isImportant = it.isImportant
            isUrgent = it.isUrgent
            location = it.location
            startDate = it.startDate
            dueDate = it.dueDate
            description = it.description
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Add New Task") })
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(16.dp).padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)

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
                Button(onClick = {
                    coroutineScope.launch {
                        println("Title: $title")
                        println("Is Important: $isImportant")
                        println("Is Urgent: $isUrgent")
                        println("Location: $location")
                        println("Start Date: $startDate")
                        println("Due Date: $dueDate")
                        println("Description: $description")

                        val task = Task(
                            title = title,
                            isImportant = isImportant,
                            isUrgent = isUrgent,
                            location = location,
                            startDate = startDate,
                            dueDate = dueDate,
                            description = description
                        )
                        db.TaskDao().insertTask(task)

                        title = ""
                        isImportant = ""
                        isUrgent = ""
                        location = ""
                        startDate = ""
                        dueDate = ""
                        description = ""
                    }
                }) {
                    Text("Submit")
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewTaskScreen() {
//    TaskScreen()
}