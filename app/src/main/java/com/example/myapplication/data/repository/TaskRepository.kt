package com.example.myapplication.data.repository

import com.example.myapplication.data.database.TaskDao
import com.example.myapplication.data.entity.Task

class TaskRepository(
    private val taskDao: TaskDao
)  {
    //add new task and keep the unique task title
    suspend fun addNewTask(task: Task): Boolean {
        if (taskDao.getTaskByTitle(task.title) == null) {
            taskDao.insertTask(task)
            return true
        } else {
            return false
        }
    }

    //show the all task list
    suspend fun getTaskList(): List<Task> {
        return taskDao.getAllTasks()
    }

    //check the task detail
    suspend fun getTaskDetail(task: Task): Task {
        return taskDao.getTaskById(task.id)
    }

    //update the task information
    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    //delete the task
    suspend fun deleteTask(task: Task) {
        taskDao.deleteTaskById(task)
    }

    //group by task
    suspend fun getClass1Tasks() = taskDao.class1()
    suspend fun getClass2Tasks() = taskDao.class2()
    suspend fun getClass3Tasks() = taskDao.class3()
    suspend fun getClass4Tasks() = taskDao.class4()
}