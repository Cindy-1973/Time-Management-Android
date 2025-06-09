package com.example.myapplication.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.AppDatabase
import com.example.myapplication.Chip
import com.example.myapplication.DatePicker
import com.example.myapplication.RadioButtonSingleSelection
import com.example.myapplication.entity.Task
import com.example.myapplication.ui.theme.DeepBlue
import kotlinx.coroutines.launch


@Composable
fun TaskScreen(modifier: Modifier = Modifier) {
    var title by remember { mutableStateOf("") }
    var classification by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    var isImportant by remember { mutableStateOf("") }
    var isUrgent by remember { mutableStateOf("") }

    var startDate by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }

    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val coroutineScope = rememberCoroutineScope()


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp).padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)

    ) {
        item {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title:") },
                maxLines = 1,
                modifier = modifier.fillMaxWidth()

            )
        }

        item {
            Column {
                Text(
                    text = "Is this task important?",
                    fontSize = 20.sp,
                    modifier = modifier.fillMaxSize()
                )
                RadioButtonSingleSelection(radioOptions = listOf("Yes", "No"), onOptionSelected = {selected -> isImportant = selected})
            }
        }

        item {
            Column {
                Text(
                    text = "Is this task urgent?",
                    fontSize = 20.sp,
                    modifier = modifier.fillMaxSize()
                )
                    RadioButtonSingleSelection(radioOptions = listOf("Yes", "No"), onOptionSelected = {selected -> isUrgent = selected})
            }
        }

        item {
            OutlinedTextField(
                value = classification,
                onValueChange = { classification = it },
                label = { Text("Classification") },
                maxLines = 1,
                modifier = modifier .fillMaxWidth()
            )
        }

        item {
            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Location") },
                maxLines = 1,
                modifier = modifier .fillMaxWidth()

            )
        }

        item {
            DatePicker("Start Date:") {
                millis -> startDate = millis.toString()

        }}


        item {
            DatePicker("Due Date:") {
                millis -> dueDate = millis.toString()
            } }

        item {
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                maxLines = 2,
                modifier = modifier .fillMaxWidth()
            )
        }

        item {
            Button(onClick = {
                coroutineScope.launch {
                    println("Title: $title")
                    println("Is Important: $isImportant")
                    println("Is Urgent: $isUrgent")
                    println("Classification: $classification")
                    println("Location: $location")
                    println("Start Date: $startDate")
                    println("Due Date: $dueDate")
                    println("Description: $description")

                    val task = Task(
                        title = title,
                        isImportant = isImportant,
                        isUrgent = isUrgent,
                        classification = classification,
                        location = location,
                        startDate = startDate,
                        dueDate = dueDate,
                        description = description
                    )
                    db.TaskDao().insertTask(task)

                    title = ""
                    isImportant = ""
                    isUrgent = ""
                    classification = ""
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


@Preview(showBackground = true)
@Composable
fun PreviewTaskScreen() {
    TaskScreen()
}