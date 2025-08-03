package com.example.myapplication.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.myapplication.data.api.QuotesAPI
import java.util.concurrent.TimeUnit

class QuotesWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val api: QuotesAPI
): CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        return try {
            val response = api.getQuote()
            if (response.isSuccessful) {
                Result.success()
            } else {
                Result.retry()
            }
        } catch (e:Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }

    companion object {
        fun scheduleQuotesWorker(context: Context) {
            val workRequest = PeriodicWorkRequestBuilder<QuotesWorker>(
                24, TimeUnit.HOURS
            )
                .setInitialDelay(24, TimeUnit.HOURS)
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "DailyQuotes",
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
        }
    }
}