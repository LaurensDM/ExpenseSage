package com.example.expensesage

import com.example.expensesage.data.UserSettingsService
import com.example.expensesage.data.currencies.CurrencyRepository
import com.example.expensesage.data.expenses.ExpenseRepository
import com.example.expensesage.ui.viewModels.SettingsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SettingsViewModelTest {
    private val testScope = StandardTestDispatcher()
    private var intervalChange = false
    private lateinit var changeInterval: () -> Unit
    private val userSettings: UserSettingsService = FakeUserSettings()
    private val expenseRepository: ExpenseRepository = FakeExpenseRepository()
    private val currencyRepository: CurrencyRepository = FakeCurrencyRepository()
    private lateinit var viewModel: SettingsViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testScope)
        changeInterval = {
            intervalChange = true
        }
        viewModel = SettingsViewModel(
            changeInterval = changeInterval,
            userSettings = userSettings,
            expenseRepository = expenseRepository,
            currencyRepository = currencyRepository,
        )
    }

    @Test
    fun testUpdateBudgetFrequency() = runTest{
        assertEquals('0',viewModel.budget.first())
        viewModel.updateBudget("100")
        assertEquals("100",viewModel.budget)
        assertEquals("Weekly",viewModel.budgetFrequencyState)
        viewModel.updateBudgetFrequency("Monthly")
        delay(1000)
        assertEquals("Monthly",viewModel.budgetFrequencyState)
        assertEquals(userSettings.budgetFrequency.first(),viewModel.budgetFrequencyState)
        assertTrue(userSettings.firstBudgetChange.first())
        assertEquals("0",viewModel.budget)
        assertTrue(intervalChange)
    }

    @Test
    fun testUpdateBudget() = runTest{
        assertEquals('0',viewModel.budget.first())
        viewModel.updateBudget("100")
        assertEquals("100",viewModel.budget)
        viewModel.updateBudget("200")
        assertEquals("200",viewModel.budget)
        viewModel.changeBudget()
        delay(1000)
        assertEquals(userSettings.budget.first(),viewModel.budget.toDouble(), 1.0)
        assertFalse(userSettings.firstBudgetChange.first())
        assertFalse(intervalChange)
    }

    @Test
    fun testUpdateCurrency() = runTest {
        assertEquals("EUR", viewModel.currency)
        viewModel.changeCurrency("USD")
        delay(1000)
        assertEquals("USD", viewModel.currency)
        viewModel.changeCurrency("JPY")
        delay(1000)
        assertEquals("JPY", viewModel.currency)
    }
}