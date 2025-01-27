package com.example.kaamchori.utils

import com.example.kaamchori.models.StructureDateTime
import java.util.Calendar
import java.util.Date

fun yearMonthDayToDate(date: StructureDateTime): Date {
    /*
    IT IS ASSUMED THAT THE DATE COMING IS IN FORMAT
    YEAR/MONTH/DAY format
     */
    val calendar = Calendar.getInstance()
    calendar.set(date.year, date.month-1, date.day,date.hour,date.minute,0) // Subtract 1 from month because Calendar months are 0-based
    return calendar.time
}