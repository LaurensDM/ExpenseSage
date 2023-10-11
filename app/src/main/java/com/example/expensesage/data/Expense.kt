package com.example.expensesage.data

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensesage.R
import java.time.LocalDate

@Entity(tableName = "expense_table")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: LocalDate = LocalDate.now(),
    @DrawableRes val imageResourceId: Int,
    val expenseName: String = "Unknown",
    val expense: Double = 0.0,
    val owed: Boolean,
)

fun populateData(): List<Expense> {
    return listOf(
        Expense(1, LocalDate.of(2021, 1, 1), R.drawable.cost, "Carrefour", 40.0, false),
        Expense(2, LocalDate.of(2022, 1, 1),  R.drawable.cost, "Delhaize", 30.0, false),
        Expense(3, LocalDate.of(2023, 8, 10),  R.drawable.cost, "Frieten", 12.34, false),
        Expense(4, LocalDate.of(2023, 9, 1),  R.drawable.owed, "Oortjes", 22.33, true),
        Expense(5, LocalDate.of(2023, 10, 1),  R.drawable.owed, "Rent", 500.0, true),
    )
}

val expenses = populateData()
