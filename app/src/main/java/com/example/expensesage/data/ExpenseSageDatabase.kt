package com.example.expensesage.data

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.expensesage.data.converter.DateConverter
import kotlinx.coroutines.CoroutineScope


@TypeConverters(value = [DateConverter::class])
@Database(entities = [Expense::class, User::class], version = 1, exportSchema = false)
abstract class ExpenseSageDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao

    companion object {
        @Volatile
        private var Instance: ExpenseSageDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ExpenseSageDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    ExpenseSageDatabase::class.java,
                    "expensesage_database"
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Use a coroutine to insert data
                        val expenseDao = Instance?.expenseDao()
                        scope.launch {
                            expenseDao?.insertAll(populateData())
                        }
                    }
                })
                    .build()
                    .also { Instance = it }
            }
        }


}
}