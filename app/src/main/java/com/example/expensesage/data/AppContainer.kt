package com.example.expensesage.data

import android.content.Context
import kotlinx.coroutines.CoroutineScope

interface AppContainer {
    val expenseRepository: ExpenseRepository
}

class AppDataContainer(private val context: Context, private val scope: CoroutineScope) : AppContainer {
    override val expenseRepository: ExpenseRepository by lazy {
        OfflineExpenseRepository(ExpenseSageDatabase.getDatabase(context, scope).expenseDao())
    }
}
