package com.example.expensesage.data

import kotlinx.coroutines.flow.Flow

class OfflineExpenseRepository(private val expenseDao: ExpenseDao) : ExpenseRepository {

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
}
