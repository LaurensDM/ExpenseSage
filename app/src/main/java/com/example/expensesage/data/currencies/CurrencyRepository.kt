package com.example.expensesage.data.currencies

import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    fun getAllCurrencies(): Flow<List<Currency>>

    fun getCurrency(id: String): Flow<Currency>

    suspend fun insert(currency: Currency)

    suspend fun insertAll(currencies: List<Currency>)

    suspend fun update(currency: Currency)

    suspend fun delete(currency: Currency)
}