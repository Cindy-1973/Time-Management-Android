package com.example.myapplication.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.entity.Task

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM Task")
    suspend fun getAllTasks(): List<Task>

    @Update
    suspend fun updateTask(updatedTask: Task)

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTaskById(id: kotlin.Int): Task?
}