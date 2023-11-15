package com.example.expensesage.data.converter

import androidx.room.TypeConverter
import java.time.LocalDateTime

/**
 * Date Converter for Room Database
 *
 */
open class DateConverter {
    @TypeConverter
    fun toDate(date: String?): LocalDateTime? {
        return LocalDateTime.parse(date)
    }

    @TypeConverter
    fun fromDate(date: LocalDateTime?): String? {
        return date?.toString()
    }


}