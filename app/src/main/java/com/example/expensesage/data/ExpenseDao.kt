package com.example.expensesage.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(expense: Expense)

    @Update
    suspend fun update(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("SELECT * FROM expense_table WHERE owed = :owed ORDER BY date ASC")
    fun getExpenses(owed: Boolean): Flow<List<Expense>>

    @Query("SELECT * FROM expense_table ORDER BY date ASC LIMIT 5")
    fun getTop5Expenses(): Flow<List<Expense>>

    @Query("SELECT * FROM expense_table WHERE id = :id")
    fun getExpense(id: Int): Flow<Expense>

}