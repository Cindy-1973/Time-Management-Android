package com.example.myapplication

import android.app.Application
import com.example.myapplication.data.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModules)
            androidContext(applicationContext)
//            workManagerFactory()
        }
    }
}