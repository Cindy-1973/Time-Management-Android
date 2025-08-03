package com.example.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.entity.Task

@Database(
    entities = [Task::class],
    version = 1
)
@TypeConverters
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao() : TaskDao
}
