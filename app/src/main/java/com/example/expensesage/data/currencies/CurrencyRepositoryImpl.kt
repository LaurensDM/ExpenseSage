package com.example.expensesage.data.currencies

import com.example.expensesage.network.CurrencyApiExecutor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.serialization.json.jsonPrimitive

class CurrencyRepositoryImpl(private val currencyDao: CurrencyDao, private val api :CurrencyApiExecutor): CurrencyRepository {
    override suspend fun getAllCurrencies(): Flow<List<Currency>> {
        val dbCurrencies = currencyDao.getAllCurrencies()
        if (dbCurrencies.isEmpty()) {
            val onlineCurrencies = api.getCurrencyRates()
            currencyDao.insertAll(onlineCurrencies)
        }
        return currencyDao.getAllCurrenciesFlow()
    }

    override suspend fun getCurrency(id: String): Flow<Currency> {
        val dbCurrencies = currencyDao.getAllCurrencies()
        if (dbCurrencies.isEmpty()) {
            val onlineCurrencies = api.getCurrencyRates()
            currencyDao.insertAll(onlineCurrencies)
        }
        return currencyDao.getCurrencyFlow(id)
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