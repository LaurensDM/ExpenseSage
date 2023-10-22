package com.example.expensesage.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: LocalDate = LocalDate.now(),
    val name: String = "Unknown",
    val monthlyMoney: Double = 0.0,
    var money: Double = 0.0,
)
