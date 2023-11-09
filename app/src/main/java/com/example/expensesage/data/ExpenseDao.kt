package com.example.expensesage.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.expensesage.ui.utils.ExpenseSummaryItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(expense: Expense)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(expenses: List<Expense>)

    @Update
    suspend fun update(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("SELECT * FROM expense_table ORDER BY date DESC")
    fun getAllExpenses(): Flow<List<Expense>>

    @Query("SELECT * FROM expense_table WHERE owed = :owed ORDER BY date DESC")
    fun getExpenses(owed: Boolean): Flow<List<Expense>>

    @Query("SELECT * FROM expense_table ORDER BY date DESC LIMIT 5")
    fun getTop5Expenses(): Flow<List<Expense>>

    @Query("SELECT * FROM expense_table WHERE id = :id")
    fun getExpense(id: Int): Flow<Expense>

    @Query("SELECT category as subject, SUM(amount) AS totalExpense FROM expense_table WHERE SUBSTR(date, 1, 4) = :searchQuery AND owed = 0 GROUP BY category")
    fun getSumOfCategory(searchQuery: String): Flow<List<ExpenseSummaryItem>>

    @Query("SELECT SUM(amount) FROM expense_table WHERE owed = :owed")
    fun getSumOfOwed(owed: Boolean): Flow<Double>

    @Query("SELECT SUM(amount) FROM expense_table")
    fun getSumOfAll(): Flow<Double>

    @Query("SELECT SUM(amount) FROM expense_table WHERE SUBSTR(date, 1, 7) = :searchQuery AND owed = 0")
    fun getSumOfDate(searchQuery: String): Flow<Double>

    @Query("SELECT SUBSTR(date, 1, 7) AS subject, SUM(amount) AS totalExpense FROM expense_table WHERE SUBSTR(date, 1, 4) = :year AND owed = 0 GROUP BY subject")
    fun getMonthlyExpenseSummary(year: String): Flow<List<ExpenseSummaryItem>>

    @Query("SELECT SUM(amount) FROM expense_table WHERE category LIKE :searchQuery AND SUBSTR(date, 1, 7) = :date AND owed = 0")
    fun getSumOfCategoryAndDate(searchQuery: String, date: String): Flow<Double>

    @Query(
        "SELECT strftime('%W', date) AS subject, SUM(amount) AS totalExpense " +
                "FROM expense_table " +
                "WHERE strftime('%Y-%m', date) = :yearMonth AND owed = 0 " +
                "GROUP BY subject"
    )
    fun getWeeklyExpensesForCurrentMonth(yearMonth: String): Flow<List<ExpenseSummaryItem>>


    @Query("SELECT SUM(amount) FROM expense_table WHERE strftime('%Y-%W', datetime(date)) = strftime('%Y-%W', 'now') AND owed = 0;")
    fun getSumOfWeek(): Flow<Double>

    @Query("SELECT SUM(amount) FROM expense_table WHERE strftime('%Y-%m', datetime(date)) = strftime('%Y-%m', 'now') AND owed = 0;")
    fun getSumOfMonth(): Flow<Double>

    @Query("SELECT SUM(amount) FROM expense_table WHERE strftime('%Y', datetime(date)) = strftime('%Y', 'now') AND owed = 0;")
    fun getSumOfYear(): Flow<Double>
}
