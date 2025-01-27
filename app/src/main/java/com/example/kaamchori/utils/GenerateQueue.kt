package com.example.kaamchori.utils

import android.util.Log
import com.example.kaamchori.models.StructureDateTime
import com.example.kaamchori.models.StructureGenericTask
import com.example.kaamchori.singletonClass.GlobalVariables
import java.util.Calendar
import java.util.Date
import kotlin.math.max
import kotlin.math.min

fun generateQueue() {
    var queueList : MutableList<StructureGenericTask> = mutableListOf<StructureGenericTask>()
    val currentTime : Date = Calendar.getInstance().time

    for ((i,task) in GlobalVariables.oneTimeTasksList.withIndex()){
        if (task.status) continue
        val startTime : Date = yearMonthDayToDate(task.startDate)
        Log.i("GENERATE_QUEUE","Current Time: $currentTime\nStart Time: $startTime")
        if (startTime < currentTime){
            var thisTask : StructureGenericTask = StructureGenericTask(
                0,
                0,
                0,
                i,
                task.taskDescription,
                task.startDate,
                task.endDate
            )
            queueList.add(thisTask)
        }
    }

    refreshRecurringTasks()

    for ((i,task) in GlobalVariables.recurringTasksList.withIndex()){
        if (task.status) continue
        val startTime : Date = yearMonthDayToDate(task.startDate)
        val endTime : Date = yearMonthDayToDate(task.endDate)

        if (startTime < currentTime && endTime > currentTime){
            var thisTask : StructureGenericTask = StructureGenericTask(
                1,
                i,
                0,
                0,
                task.taskDescription,
                task.startDate,
                task.endDate
            )
            queueList.add(thisTask)
        }
    }

    for ((i,task) in GlobalVariables.multipleTasksList.withIndex()){
        if (task.status == task.totalQty) continue
        val startTime : Date = yearMonthDayToDate(task.startDate)
        val endTime : Date = yearMonthDayToDate(task.endDate)

        val taskDuration : Long = (endTime.time - startTime.time)/task.totalQty
        var shouldHaveBeenCompleted : Int = ((currentTime.time - startTime.time)/taskDuration).toInt()
        shouldHaveBeenCompleted = min(shouldHaveBeenCompleted,task.totalQty)

        for (j in task.status..shouldHaveBeenCompleted){
            val thisTaskStartTime : StructureDateTime = getYearMonthDay( Date(taskDuration*j+startTime.time))
            val thisTaskEndTime : StructureDateTime = getYearMonthDay(Date((j+1)*taskDuration + startTime.time))


            var thisTask : StructureGenericTask = StructureGenericTask(
                2,
                0,
                j,
                0,
                task.taskDescription,
                thisTaskStartTime,
                thisTaskEndTime
            )
            queueList.add(thisTask)
        }
    }
    queueList = queueList.sortedBy { yearMonthDayToDate(it.taskDeadline).time}.toMutableList()
    GlobalVariables.queueTasksList = queueList
}