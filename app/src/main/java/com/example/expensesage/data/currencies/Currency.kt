package com.example.expensesage.data.currencies

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Currency Entity
 *
 * @property currencyCode Currency code (e.g. EUR)
 * @property date Date
 * @property rate Rate
 * @property comparedCurrency Compared currency (e.g. USD)
 */
@Entity(tableName = "currency_table")
data class Currency(
    @PrimaryKey(autoGenerate = false) val currencyCode: String,
    val date: String,
    val rate: Double,
    val comparedCurrency: String,
)