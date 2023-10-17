package com.example.expensesage.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.expensesage.data.converter.DateConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@TypeConverters(value = [DateConverter::class])
@Database(
    entities = [Expense::class, User::class],
    version = 2,
    exportSchema = false
)
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
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Use a coroutine to insert data
                        val expenseDao = Instance?.expenseDao()
                        scope.launch {
                            expenseDao?.insertAll(populateData())
                        }
                    }
                })
//                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }


    }

//    @DeleteTable(tableName = "user_table")
//    class UserDeleteMigration : AutoMigrationSpec{
//        @Override
//        override fun onPostMigrate(db: SupportSQLiteDatabase) {
//            // Invoked once auto migration is done
//        }
//    }
}

