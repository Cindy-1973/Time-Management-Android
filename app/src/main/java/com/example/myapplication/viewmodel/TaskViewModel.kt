package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.entity.Task
import com.example.myapplication.data.repository.TaskRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel(
    private val taskRepository: TaskRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
): ViewModel() {
    private val _uiState = MutableStateFlow(TasksUIState())
    val uiState: StateFlow<TasksUIState> = _uiState.asStateFlow()
    private val scope = CoroutineScope(dispatcher + SupervisorJob())
    init {
        getTaskList()
    }

    fun addNewTask(task: Task) {
        scope.launch {
            try {
                val success = taskRepository.addNewTask(task)
                if (success) {
                    _uiState.update { it.copy(operationMessage = "This task has added") }
                } else {
                    _uiState.update { it.copy(operationMessage = "Title already exists") }
                }
                getTaskList()
            } catch (e: Exception) {
                _uiState.update { it.copy(operationMessage = e.message ?: "Add failed") }
            }
        }
    }

    private fun getTaskList() {
        scope.launch {
            taskRepository.getTaskList()
            _uiState.update { it.copy(isLoading = true, operationMessage = null) }
            try {
                val list = taskRepository.getTaskList()
                _uiState.update { it.copy(taskList = list, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        operationMessage = e.message ?: "Failed to load tasks"
                    )
                }
            }
        }
    }

    fun updateTask(task: Task) {
        scope.launch {
            try {
                taskRepository.updateTask(task)
                _uiState.update { it.copy(operationMessage = "Updated") }
                getTaskList()
            } catch (e: Exception) {
                _uiState.update { it.copy(operationMessage = e.message ?: "Update error") }
            }
        }
    }

    fun deleteTask(task: Task) {
        scope.launch {
            try {
                taskRepository.deleteTask(task)
                _uiState.update { it.copy(operationMessage = "This task has deleted.") }
                getTaskList()
            } catch (e: Exception) {
                _uiState.update { it.copy(operationMessage = e.message?: "This task deleted failed") }
            }
        }
    }

    suspend fun getTaskDetails(task: Task): Task? {
        return try {
            taskRepository.getTaskDetail(task)
        } catch (e: Exception) {
            null
        }
    }

    fun clearMessage() {
        _uiState.update { it.copy(operationMessage = null) }
    }
}

