package com.example.expensesage

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.testing.TestWorkerBuilder
import androidx.work.testing.WorkManagerTestInitHelper
import com.example.expensesage.workers.BudgetWorker
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.TestScope
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@RunWith(AndroidJUnit4::class)
class BudgetWorkerTest {
//    @get:Rule
//    val instantTaskExecutorRule = InstantTaskExecutorRule()


    private lateinit var testScope: TestScope
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        WorkManagerTestInitHelper.initializeTestWorkManager(
            context
        )
        testScope = TestScope()
    }

    @Test
    fun testBudgetWorker() {
        val worker =
            TestListenableWorkerBuilder<BudgetWorker>(context = context)
                .build()
    }

}