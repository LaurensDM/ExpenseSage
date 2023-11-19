package com.example.expensesage

import com.example.expensesage.data.expenses.Expense
import com.example.expensesage.ui.viewModels.ListUiState
import com.example.expensesage.ui.viewModels.ListViewModel
import com.example.expensesage.ui.viewModels.MapUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.* // ktlint-disable no-wildcard-imports
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ListViewModelTest {
    private val testScope = StandardTestDispatcher()
    private val fakeExpenseRepository = FakeExpenseRepository()
    private lateinit var viewModel: ListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testScope)
        viewModel = ListViewModel(fakeExpenseRepository)
    }


    @Test
    fun `test viewModel get5Expenses`() = runTest {
        assertTrue(viewModel.listUiState is ListUiState.Loading)
        viewModel.get5Expenses()
        delay(1000)
        assertTrue(viewModel.listUiState is ListUiState.Success)
    }

    @Test
    fun `test viewModel getExpenses`() = runTest {
        assertTrue(viewModel.mapUiState is MapUiState.Loading)
        viewModel.getExpenses(true)
        delay(1000)
        assertTrue(viewModel.mapUiState is MapUiState.Success)
    }
}
