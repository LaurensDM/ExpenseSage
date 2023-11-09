package com.example.expensesage.data.expenses

import com.example.expensesage.ui.utils.ExpenseSummaryItem
import kotlinx.coroutines.flow.Flow

class OfflineExpenseRepository(private val expenseDao: ExpenseDao) : ExpenseRepository {
    override fun getAllExpenses(): Flow<List<Expense>> {
        return expenseDao.getAllExpenses()
    }

    override fun getExpenses(owed: Boolean): Flow<List<Expense>> {
        return expenseDao.getExpenses(owed)
    }

    override fun getTop5Expenses(): Flow<List<Expense>> {
        return expenseDao.getTop5Expenses()
    }

    override fun getExpense(id: Int): Flow<Expense> {
        return expenseDao.getExpense(id)
    }

    override suspend fun insert(expense: Expense) {
        expenseDao.insert(expense)
    }

    override suspend fun update(expense: Expense) {
        expenseDao.update(expense)
    }

    override suspend fun delete(expense: Expense) {
        expenseDao.delete(expense)
    }

    override fun getSumOfCategory(searchQuery: String): Flow<List<ExpenseSummaryItem>> {
        return expenseDao.getSumOfCategory(searchQuery)
    }

    override fun getSumOfOwed(owed: Boolean): Flow<Double> {
        return expenseDao.getSumOfOwed(owed)
    }

    override fun getSumOfAll(): Flow<Double> {
        return expenseDao.getSumOfAll()
    }

    override fun getMonthlyExpenseSummary(year: String): Flow<List<ExpenseSummaryItem>> {
        return expenseDao.getMonthlyExpenseSummary(year)
    }

    override fun getSumOfDate(searchQuery: String): Flow<Double> {
        return expenseDao.getSumOfDate(searchQuery)
    }

    override fun getSumOfCategoryAndDate(searchQuery: String, date: String): Flow<Double> {
        return expenseDao.getSumOfCategoryAndDate(searchQuery, date)
    }

    override fun getWeeklyExpenseSummary(yearMonth: String): Flow<List<ExpenseSummaryItem>> {
        return expenseDao.getWeeklyExpensesForCurrentMonth(yearMonth)
    }

    override fun getSumOfWeek(): Flow<Double> {
        return expenseDao.getSumOfWeek()
    }

    override fun getSumOfMonth(): Flow<Double> {
        return expenseDao.getSumOfMonth()
    }

    override fun getSumOfYear(): Flow<Double> {
        return expenseDao.getSumOfYear()
    }
}
