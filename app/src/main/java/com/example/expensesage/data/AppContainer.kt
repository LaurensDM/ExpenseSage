package com.example.expensesage.data

import android.content.Context
import com.example.expensesage.data.currencies.CurrencyRepository
import com.example.expensesage.data.currencies.OfflineCurrencyRepository
import com.example.expensesage.data.expenses.ExpenseRepository
import com.example.expensesage.data.expenses.OfflineExpenseRepository
import kotlinx.coroutines.CoroutineScope

interface AppContainer {
    val expenseRepository: ExpenseRepository
    val currencyRepository: CurrencyRepository
}

class AppDataContainer(private val context: Context, private val scope: CoroutineScope) : AppContainer {
    override val expenseRepository: ExpenseRepository by lazy {
        OfflineExpenseRepository(ExpenseSageDatabase.getDatabase(context, scope).expenseDao())
    }
    override val currencyRepository: CurrencyRepository by lazy {
        OfflineCurrencyRepository(ExpenseSageDatabase.getDatabase(context, scope).currencyDao())
    }
}
