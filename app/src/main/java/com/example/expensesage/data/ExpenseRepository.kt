package com.example.expensesage.data

import com.example.expensesage.ui.utils.ExpenseSummaryItem
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    fun getAllExpenses(): Flow<List<Expense>>
    fun getExpenses(owed: Boolean): Flow<List<Expense>>
    fun getTop5Expenses(): Flow<List<Expense>>
    fun getExpense(id: Int): Flow<Expense>
    suspend fun insert(expense: Expense)
    suspend fun update(expense: Expense)
    suspend fun delete(expense: Expense)

    fun getSumOfCategory(searchQuery: String): Flow<List<ExpenseSummaryItem>>

    fun getSumOfOwed(owed: Boolean): Flow<Double>

    fun getSumOfAll(): Flow<Double>

    fun getMonthlyExpenseSummary(year: String): Flow<List<ExpenseSummaryItem>>

    fun getSumOfDate(searchQuery: String): Flow<Double>

    fun getSumOfCategoryAndDate(searchQuery: String, date: String): Flow<Double>

    fun getWeeklyExpenseSummary(yearMonth: String): Flow<List<ExpenseSummaryItem>>

    fun getSumOfWeek() : Flow<Double>

    fun getSumOfMonth() : Flow<Double>

    fun getSumOfYear() : Flow<Double>
}
