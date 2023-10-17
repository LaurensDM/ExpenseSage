package com.example.expensesage.data

import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    fun getExpenses(owed: Boolean): Flow<List<Expense>>
    fun getTop5Expenses(): Flow<List<Expense>>
    fun getExpense(id: Int): Flow<Expense>
    suspend fun insert(expense: Expense)
    suspend fun update(expense: Expense)
    suspend fun delete(expense: Expense)
}