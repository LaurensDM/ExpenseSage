package com.example.expensesage.data.currencies

import kotlinx.coroutines.flow.Flow

class OfflineCurrencyRepository(private val currencyDao: CurrencyDao): CurrencyRepository {
    override fun getAllCurrencies(): Flow<List<Currency>> {
        return currencyDao.getAllCurrencies()
    }

    override fun getCurrency(id: String): Flow<Currency> {
        return currencyDao.getCurrency(id)
    }

    override suspend fun insert(currency: Currency) {
        currencyDao.insert(currency)
    }

    override suspend fun insertAll(currencies: List<Currency>) {
        currencyDao.insertAll(currencies)
    }

    override suspend fun update(currency: Currency) {
        currencyDao.update(currency)
    }

    override suspend fun delete(currency: Currency) {
        currencyDao.delete(currency)
    }
}