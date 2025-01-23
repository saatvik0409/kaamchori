package com.example.kaamchori.utils

import com.example.kaamchori.models.StructureDate
import java.util.Calendar
import java.util.Date

fun yearMonthDayToDate(date: StructureDate): Date {
    /*
    IT IS ASSUMED THAT THE DATE COMING IS IN FORMAT
    YEAR/MONTH/DAY format
     */
    val calendar = Calendar.getInstance()
    calendar.set(date.year, date.month-1, date.day) // Subtract 1 from month because Calendar months are 0-based
    return calendar.time
}