package com.example.expensesage.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.expensesage.data.converter.DateConverter
import com.example.expensesage.data.currencies.Currency
import com.example.expensesage.data.currencies.CurrencyDao
import com.example.expensesage.data.expenses.Expense
import com.example.expensesage.data.expenses.ExpenseDao
import kotlinx.coroutines.CoroutineScope

@TypeConverters(value = [DateConverter::class])
@Database(
    entities = [Expense::class, Currency::class],
    version = 5,
    exportSchema = false,
)
abstract class ExpenseSageDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao
    abstract fun currencyDao(): CurrencyDao

    companion object {
        @Volatile
        private var Instance: ExpenseSageDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ExpenseSageDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    ExpenseSageDatabase::class.java,
                    "expensesage_database",
                ).addMigrations(
                    object : Migration(1, 2) {
                        override fun migrate(database: SupportSQLiteDatabase) {
                            database.execSQL("DROP TABLE user_table")
                        }
                    },
                    object : Migration(2, 3) {
                        override fun migrate(database: SupportSQLiteDatabase) {
                            database.execSQL("DROP TABLE expense_table")
                            database.execSQL("CREATE TABLE expense_table (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, date TEXT NOT NULL, imageResourceId INTEGER NOT NULL, expenseName TEXT NOT NULL, expense REAL NOT NULL, owed INTEGER NOT NULL)")
                        }
                    },
                    object : Migration(3, 4) {
                        override fun migrate(database: SupportSQLiteDatabase) {
                            database.execSQL("DROP TABLE expense_table")
                            database.execSQL("CREATE TABLE expense_table (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, date TEXT NOT NULL, imageResourceId INTEGER NOT NULL, name TEXT NOT NULL, amount REAL NOT NULL, owed INTEGER NOT NULL, category TEXT NOT NULL)")
                        }
                    },
                    object : Migration(4, 5) {
                        override fun migrate(database: SupportSQLiteDatabase) {
                            database.execSQL("CREATE TABLE IF NOT EXISTS currency_table (" +
                                    "currencyCode TEXT NOT NULL, " +
                                    "date TEXT NOT NULL, " +
                                    "rate REAL NOT NULL, " +
                                    "comparedCurrency TEXT NOT NULL, " +
                                    "PRIMARY KEY(currencyCode)" +
                                    ")")
                        }
                    },
                )

                    // Populate the database with data

//                    .addCallback(object : Callback() {
//                        override fun onCreate(db: SupportSQLiteDatabase) {
//                            super.onCreate(db)
//                            // Use a coroutine to insert data
//                            val expenseDao = Instance?.expenseDao()
//                            scope.launch {
//                                expenseDao?.insertAll(populateData())
//                            }
//                        }
//                    })

                    .build()
                    .also { Instance = it }
            }
        }
    }
}
