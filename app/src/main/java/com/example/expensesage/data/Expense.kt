package com.example.expensesage.data

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensesage.R
import java.time.LocalDate

@Entity(tableName = "expense_table", primaryKeys = ["id"])
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: LocalDate = LocalDate.now(),
    @DrawableRes val imageResourceId: Int,
    val expenseName: String = "Unknown",
    val expense: Double = 0.0,
    val owed: Boolean,
    )

val expenses = listOf(
//    Expense(LocalDate.of(2023,10,7),R.drawable.cost, "Delhaize", 16.59, false ),
//    Expense(LocalDate.of(2023,10,6),R.drawable.owed, "Joris frieten", 6.00, true),
//    Expense(LocalDate.of(2023,8,7),R.drawable.cost, "Delhaize", 40.00, false ),
//    Expense(LocalDate.of(2001,7,7),R.drawable.owed, "Alex brooodje", 4.00, true),
//    Expense(LocalDate.of(2022,10,7),R.drawable.cost, "Campus", 6.59, false ),
    Expense(1,LocalDate.of(2023,10,7),R.drawable.cost, "Delhaize", 16.59, false ),
).sortedByDescending { it.date }
