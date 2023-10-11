package com.example.expensesage.data.converter

import androidx.room.TypeConverter
import java.time.LocalDate

open class DateConverter {
    @TypeConverter
    fun toDate(date: String?): LocalDate? {
        return LocalDate.parse(date)
    }

    @TypeConverter
    fun fromDate(date: LocalDate?): String? {
        return date?.toString()
    }


}