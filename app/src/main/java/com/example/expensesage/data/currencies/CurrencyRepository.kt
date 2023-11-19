package com.example.expensesage.data.currencies

import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    /**
     * Get all currencies
     *
     * @return Flow<List<Currency>> List of currencies
     */
    suspend fun getAllCurrencies(): Flow<List<Currency>>

    /**
     * Get currency by id
     *
     * @param id String Currency code (e.g. EUR)
     * @return Flow<Currency> Currency
     */
    suspend fun getCurrency(id: String): Flow<Currency>

    suspend fun insert(currency: Currency)

    suspend fun insertAll(currencies: List<Currency>)

    suspend fun update(currency: Currency)

    suspend fun delete(currency: Currency)
}