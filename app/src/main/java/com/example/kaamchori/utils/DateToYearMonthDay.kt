package com.example.kaamchori.utils

import com.example.kaamchori.models.StructureDateTime
import java.util.Calendar
import java.util.Date

fun getYearMonthDay (dateInstance: Date) : StructureDateTime {
    val calendar = Calendar.getInstance()
    calendar.time = dateInstance

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)+1 // Adding 1 since Calendar.MONTH is 0-based
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val hour = calendar.get(Calendar.HOUR_OF_DAY) // 24-hour format
    val minute = calendar.get(Calendar.MINUTE)
    val second = calendar.get(Calendar.SECOND)

    return StructureDateTime(year,month,day,hour,minute,second)
}