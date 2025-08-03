package com.example.myapplication.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.data.entity.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    //show the list of tasks in task list screen
    @Query("SELECT * FROM Task")
    suspend fun getAllTasks(): List<Task>

    //create a new task, keep the unique task title
    @Query("SELECT * FROM Task WHERE title = :title LIMIT 1")
    suspend fun getTaskByTitle(title: String): Task?
    @Insert
    suspend fun insertTask(task: Task)

    //check the task detail
    @Query("SELECT * FROM Task WHERE id = :id LIMIT 1")
    suspend fun getTaskById(id: Int): Task

    //update the existing task
    @Update
    suspend fun updateTask(task: Task)

    //delete the task
    @Delete
    suspend fun deleteTaskById(task: Task)

    //group by important and urgent
    @Query("SELECT * FROM Task WHERE isImportant = 'yes' AND isUrgent = 'yes'")
    suspend fun class1(): List<Task>

    @Query("SELECT * FROM Task WHERE isImportant = 'yes' AND isUrgent = 'no'")
    suspend fun class2(): List<Task>

    @Query("SELECT * FROM Task WHERE isImportant = 'no' AND isUrgent = 'yes'")
    suspend fun class3(): List<Task>

    @Query("SELECT * FROM Task WHERE isImportant = 'no' AND isUrgent = 'no'")
    suspend fun class4(): List<Task>
}