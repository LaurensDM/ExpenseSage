package com.example.expensesage

import com.example.expensesage.data.UserSettingsService
import com.example.expensesage.data.expenses.ExpenseRepository
import com.example.expensesage.ui.viewModels.StatisticUiState
import com.example.expensesage.ui.viewModels.StatisticViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class StatisticViewModelTest {
    private val testScope = StandardTestDispatcher()
    private val userSettings: UserSettingsService = FakeUserSettings()
    private val expenseRepository: ExpenseRepository = FakeExpenseRepository()
    private lateinit var viewModel: StatisticViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testScope)
        viewModel = StatisticViewModel(
            expenseRepository = expenseRepository,
            userPref = userSettings,
        )
    }

    @Test
    fun testInitialised() = runTest{
        assertTrue(viewModel.statisticUiState is StatisticUiState.Success)
    }

    @Test
    fun testGetData() = runTest{
        viewModel.getData()
        assertTrue(viewModel.statisticUiState is StatisticUiState.Success)
    }
}