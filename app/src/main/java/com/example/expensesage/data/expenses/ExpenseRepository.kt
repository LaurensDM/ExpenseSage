package com.example.expensesage.data.expenses

import com.example.expensesage.ui.utils.ExpenseSummaryItem
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    /**
     * Get all expenses
     *
     * @return Flow<List<Expense>> List of expenses
     */
    fun getAllExpenses(): Flow<List<Expense>>

    /**
     * Get all expenses that are owed or not owed
     *
     * @param owed Boolean
     * @return Flow<List<Expense>> List of expenses
     */
    fun getExpenses(owed: Boolean): Flow<List<Expense>>

    /**
     * Get 5 most recent expenses
     *
     * @return Flow<List<Expense>> List of expenses
     */
    fun getTop5Expenses(): Flow<List<Expense>>

    /**
     * Get expense by id
     *
     * @param id Int id of expense
     * @return Flow<Expense> Expense
     */
    fun getExpense(id: Int): Flow<Expense>
    suspend fun insert(expense: Expense)
    suspend fun update(expense: Expense)
    suspend fun delete(expense: Expense)

    /**
     * Get sum of expenses by category
     *
     * @param searchQuery String category
     * @return Flow<List<ExpenseSummaryItem>> List of expenses
     */
    fun getSumOfCategory(searchQuery: String): Flow<List<ExpenseSummaryItem>>

    /**
     * Get sum of expenses that are owed or not owed
     *
     * @param owed Boolean owed or not owed
     * @return  Flow<Double> sum of expenses
     */
    fun getSumOfOwed(owed: Boolean): Flow<Double>

    /**
     * Get sum of all expenses
     *
     * @return Flow<Double> sum of expenses
     */
    fun getSumOfAll(): Flow<Double>

    /**
     * Get expenses for all months in a year
     *
     * @param year String year
     * @return Flow<List<ExpenseSummaryItem>> List of expenses for each month
     */
    fun getMonthlyExpenseSummary(year: String): Flow<List<ExpenseSummaryItem>>

    /**
     * Get sum of expenses of a certain date
     *
     * @param searchQuery String date
     * @return Flow<Double> sum of expenses
     */
    fun getSumOfDate(searchQuery: String): Flow<Double>

    /**
     * Get sum of expenses of a certain category and date
     *
     * @param searchQuery String category
     * @param date String date
     * @return Flow<Double> sum of expenses
     */
    fun getSumOfCategoryAndDate(searchQuery: String, date: String): Flow<Double>

    /**
     * Get expenses for all weeks in a month
     *
     * @param yearMonth String year and month
     * @return Flow<List<ExpenseSummaryItem>> List of expenses for each week
     */
    fun getWeeklyExpenseSummary(yearMonth: String): Flow<List<ExpenseSummaryItem>>

    /**
     * Get sum of expenses for current week
     *
     * @return
     */
    fun getSumOfWeek() : Flow<Double>

    /**
     * Get sum of expenses for current month
     *
     * @return
     */
    fun getSumOfMonth() : Flow<Double>

    /**
     * Get sum of expenses for current year
     *
     * @return Flow<Double> sum of expenses
     */
    fun getSumOfYear() : Flow<Double>
}
