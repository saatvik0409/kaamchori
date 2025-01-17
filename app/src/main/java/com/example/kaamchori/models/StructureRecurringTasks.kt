package com.example.kaamchori.models

import android.app.ActivityManager.TaskDescription
import java.util.Date

data class StructureRecurringTasks(
    val taskDescription : String,
    val startDate : Date,
    val endDate : Date,
    val frequency : Int, //Currently in hours, later we can increase granuality
    val status : Boolean
)