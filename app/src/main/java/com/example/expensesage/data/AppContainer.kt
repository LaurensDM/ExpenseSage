package com.example.expensesage.data

import android.content.Context

interface AppContainer {
    val expenseRepository: ExpenseRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val expenseRepository: ExpenseRepository by lazy {
        OfflineExpenseRepository(ExpenseSageDatabase.getDatabase(context).expenseDao())
    }

}