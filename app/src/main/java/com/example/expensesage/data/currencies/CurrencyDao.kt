package com.example.expensesage.data.currencies

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currency: Currency)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currencies: List<Currency>)

    @Update
    suspend fun update(currency: Currency)

    @Delete
    suspend fun delete(currency: Currency)

    /**
     * Get all currencies
     *
     * @return Flow<List<Currency>> List of currencies
     */
    @Query("SELECT * FROM currency_table ORDER BY currencyCode ASC")
    fun getAllCurrencies(): Flow<List<Currency>>

    /**
     * Get currency
     *
     * @param id String Currency code (e.g. EUR)
     * @return Flow<Currency> Currency
     */
    @Query("SELECT * FROM currency_table WHERE currencyCode = :id")
    fun getCurrency(id: String): Flow<Currency>
}