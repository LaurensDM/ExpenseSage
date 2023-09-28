package com.example.expensesage.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.expensesage.R

data class Expense(
    @DrawableRes val imageResourceId: Int,
    val expenseName: String,
    val expense: Double,
    val owed: Boolean,
    )

val expenses = listOf(
    Expense(R.drawable.cost, "Delhaize", 16.59, false ),
    Expense(R.drawable.owed, "Joris frieten", 6.00, true),
    Expense(R.drawable.cost, "Delhaize", 40.00, false ),
    Expense(R.drawable.owed, "Alex brooodje", 4.00, true),
    Expense(R.drawable.cost, "Campus", 6.59, false ),
)
