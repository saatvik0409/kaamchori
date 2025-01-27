package com.example.kaamchori.utils

import com.example.kaamchori.singletonClass.GlobalVariables
import java.util.Calendar
import java.util.Date

fun refreshRecurringTasks(){

    val calendar : Calendar = Calendar.getInstance()

    for ((i,task) in GlobalVariables.recurringTasksList.withIndex()){
        val taskEndDate : Date = yearMonthDayToDate(task.endDate)
        val taskStartDate : Date = yearMonthDayToDate(task.startDate)
        val currentTime : Date = calendar.time

        if (taskEndDate < currentTime){
            calendar.time = taskStartDate
            calendar.add(Calendar.HOUR_OF_DAY,task.frequency)
            val newStartDate : Date = calendar.time
            calendar.time = taskEndDate
            calendar.add(Calendar.HOUR_OF_DAY,task.frequency)
            val newEndDate : Date = calendar.time

            task.startDate = getYearMonthDay(newStartDate)
            task.endDate = getYearMonthDay(newEndDate)
            task.status = false
        }
    }
}