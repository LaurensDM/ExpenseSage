package com.example.expensesage.data.currencies

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_table")
data class Currency(
    @PrimaryKey(autoGenerate = false) val currencyCode: String,
    val date: String,
    val rate: Double,
    val comparedCurrency: String,
)