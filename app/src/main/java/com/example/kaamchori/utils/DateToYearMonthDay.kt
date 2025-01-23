package com.example.kaamchori.utils

import com.example.kaamchori.models.StructureDate
import java.util.Calendar
import java.util.Date

fun getYearMonthDay (dateInstance: Date) : StructureDate {
    val calendar = Calendar.getInstance()
    calendar.time = dateInstance

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) // Adding 1 since Calendar.MONTH is 0-based
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    return StructureDate(year,month,day)
}