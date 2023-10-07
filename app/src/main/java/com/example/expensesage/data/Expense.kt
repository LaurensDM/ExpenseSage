package com.example.expensesage.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.expensesage.R
import java.time.LocalDate
import java.util.Date

data class Expense(
    val date: LocalDate = LocalDate.now(),
    @DrawableRes val imageResourceId: Int,
    val expenseName: String = "Unknown",
    val expense: Double = 0.0,
    val owed: Boolean,
    )

val expenses = listOf(
    Expense(LocalDate.of(2023,10,7),R.drawable.cost, "Delhaize", 16.59, false ),
    Expense(LocalDate.of(2023,10,6),R.drawable.owed, "Joris frieten", 6.00, true),
    Expense(LocalDate.of(2023,8,7),R.drawable.cost, "Delhaize", 40.00, false ),
    Expense(LocalDate.of(2001,7,7),R.drawable.owed, "Alex brooodje", 4.00, true),
    Expense(LocalDate.of(2022,10,7),R.drawable.cost, "Campus", 6.59, false ),
).sortedByDescending { it.date }
