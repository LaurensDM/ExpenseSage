package com.example.expensesage.data.expenses

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensesage.R
import java.time.LocalDateTime

/**
 * Expense Entity
 *
 * @property id Int (auto-generated)
 * @property date LocalDateTime (auto-generated)
 * @property imageResourceId Drawable resource id
 * @property name Name of expense
 * @property amount Amount of expense
 * @property owed Boolean (true if owed, false if not)
 * @property category Category of expense
 */
@Entity(tableName = "expense_table")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: LocalDateTime = LocalDateTime.now(),
    @DrawableRes val imageResourceId: Int,
    val name: String = "Unknown",
    val amount: Double = 0.0,
    val owed: Boolean,
    val category: String = "Other",
)

/**
 * Populate data for testing
 *
 * @return List<Expense> List of expenses
 */
fun populateData(): List<Expense> {
    return listOf(
        Expense(1, LocalDateTime.of(2021, 1, 1, 10, 10), R.drawable.cost, "Carrefour", 40.0, false, "Food & Groceries"),
        Expense(2, LocalDateTime.of(2022, 1, 1, 10, 10), R.drawable.cost, "Delhaize", 30.0, false, "Food & Groceries"),
        Expense(3, LocalDateTime.of(2023, 1, 7, 10, 10), R.drawable.cost, "Frieten", 12.34, false, "Food & Groceries"),
        Expense(4, LocalDateTime.of(2023, 3, 5, 10, 10), R.drawable.owed, "Oortjes", 22.33, true, "Online Purchases"),
        Expense(5, LocalDateTime.of(2023, 8, 2, 10, 10), R.drawable.owed, "Rent", 500.0, true, "Rent"),
    )
}

val categories = listOf(
    "Food & Groceries",
    "Rent",
    "Gas",
    "Online Purchases",
    "Clothing",
    "Other",
)

val expenses = populateData()
