package com.example.expensesage

import android.icu.number.NumberFormatter
import android.icu.text.DecimalFormat
import android.icu.text.NumberFormat
import com.example.expensesage.data.expenses.Expense
import com.example.expensesage.ui.utils.ExpenseDetail
import com.example.expensesage.ui.utils.toExpense
import com.example.expensesage.ui.utils.toExpenseDetail
import com.example.expensesage.ui.viewModels.ExpenseDetailsViewModel
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
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
import java.time.LocalDateTime
import java.util.Locale

class ExpenseDetailViewModelTest {
    private val testScope = StandardTestDispatcher()
    private val fakeExpenseRepository = FakeExpenseRepository()
    private val fakeUserSettingsService = FakeUserSettings()
    private lateinit var viewModel: ExpenseDetailsViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testScope)
        viewModel = ExpenseDetailsViewModel(fakeUserSettingsService, fakeExpenseRepository, DecimalFormat())
    }

    @Test
    fun `test expense detail state`() {
        val initialState = viewModel.expenseDetailState
        assertEquals(initialState, ExpenseDetail().copy(date = viewModel.expenseDetailState.date))
        viewModel.updateState(initialState.copy(name = "test"))
        assert(viewModel.expenseDetailState.name == "test")
        viewModel.resetState()
        assert(viewModel.expenseDetailState == ExpenseDetail())

        runTest {
            viewModel.initializeState(Expense(id = 5, imageResourceId = 1, owed = false))
            delay(1000)
            assertEquals(5, viewModel.expenseDetailState.id)
        }
    }


    @Test
    fun `test name error`() {
        viewModel.updateState(viewModel.expenseDetailState.copy(name = ""))
        assert(viewModel.nameError)
        viewModel.updateState(viewModel.expenseDetailState.copy(name = "test"))
        assert(!viewModel.nameError)
    }

    @Test
    fun `test amount error`() {
        viewModel.updateState(viewModel.expenseDetailState.copy(amount = ""))
        assert(viewModel.amountError)
        viewModel.updateState(viewModel.expenseDetailState.copy(amount = "test"))
        assert(viewModel.amountError)
        viewModel.updateState(viewModel.expenseDetailState.copy(amount = "1"))
        assert(!viewModel.amountError)
    }

    @Test
    fun `test update expense`() = runTest {
        val ogExpense = fakeExpenseRepository.getExpense(1).first()

            viewModel.initializeState(ogExpense)
            delay(1000)
            assertEquals(ogExpense.id, viewModel.expenseDetailState.id)
            viewModel.updateState(viewModel.expenseDetailState.copy(name = "test"))
            viewModel.updateExpense(ogExpense)
            delay(1000)
            assertEquals(viewModel.expenseDetailState.name, fakeExpenseRepository.getExpense(1).first().name)

    }

    @Test
    fun `test insert expense`() = runTest {
        viewModel.updateState(viewModel.expenseDetailState.copy(name = "test"))
        delay(1000)
        viewModel.saveExpense()
        delay(1000)
        assertEquals(viewModel.expenseDetailState.name, fakeExpenseRepository.getExpense(6).first().name)
    }

    @Test
    fun `test delete expense`() = runTest {
        val ogExpense = fakeExpenseRepository.getExpense(1).first()
        delay(1000)
        viewModel.deleteExpense(ogExpense)
        delay(1000)
        assertEquals(4, fakeExpenseRepository.getAllExpenses().first().size)
    }

    @Test
    fun `test pay owed`() = runTest {
        val ogExpense = fakeExpenseRepository.getExpense(4).first()
        delay(1000)
        assertTrue(fakeExpenseRepository.getExpense(4).first().owed)
        viewModel.payOwed(ogExpense)
        delay(1000)
        assertFalse(fakeExpenseRepository.getExpense(4).first().owed)
    }
}