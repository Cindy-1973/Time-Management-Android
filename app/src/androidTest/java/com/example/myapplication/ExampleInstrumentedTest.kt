package com.example.myapplication

import android.widget.Advanceable
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.data.api.QuotesAPI
import com.example.myapplication.data.di.appModules
import com.example.myapplication.data.entity.Task
import com.example.myapplication.viewmodel.TaskViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import org.junit.After
import org.koin.test.KoinTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.inject
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest : KoinTest{
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.myapplication", appContext.packageName)
    }

    private val api: QuotesAPI by inject()
    //remote api test
    @Test
    fun remoteAPIQuoteTest() = runBlocking {
        val response = api.getQuote()
        if (response.isSuccessful) {
            println(response.body())
        } else {
            println(response.code())
        }
    }

    //worker test to get quote everyday
    @Test
    fun everydayAPIQuoteTest() = runBlocking {
        val response = api.getQuote()
        println(response.body())
        assertTrue(response.isSuccessful)
    }
}