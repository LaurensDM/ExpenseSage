package com.example.expensesage.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.expensesage.ui.utils.ioThread

@Database(entities = [Expense::class, User::class], version = 1, exportSchema = false)
abstract class ExpenseSageDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao

    companion object {
        @Volatile
        private var Instance: ExpenseSageDatabase? = null

        fun getDatabase(context: Context): ExpenseSageDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    ExpenseSageDatabase::class.java,
                    "expensesage_database"
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Populate the database with initial data
                        // For example:
                        val expenseDao = Instance?.expenseDao()
                        expenseDao.insertAll(populateData())
                    }
                })
                    .build()
                    .also { Instance = it }
            }
        }


}
}