package com.example.myapplication.data.di

import androidx.room.Room
import com.example.myapplication.data.api.QuotesAPI
import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.data.entity.Task
import com.example.myapplication.data.repository.TaskRepository
import com.example.myapplication.data.workers.QuotesWorker
import com.example.myapplication.viewmodel.TaskViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module
import retrofit2.Retrofit

private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

val appModules = module {
    //remote quotes api
    single {
        Retrofit.Builder()
            .baseUrl("https://favqs.com/api/")
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()
    }
    single { get<Retrofit>().create(QuotesAPI::class.java) }

    worker { QuotesWorker(get(), get(), get()) }


    //room database
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_database"
        ).build()
    }
    single { get<AppDatabase>().taskDao() }
    single<TaskRepository> { TaskRepository(get()) }

    single<TaskViewModel> { TaskViewModel(get()) }
}

