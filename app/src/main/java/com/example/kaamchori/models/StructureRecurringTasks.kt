package com.example.kaamchori.models

import android.app.ActivityManager.TaskDescription
import android.os.Parcel
import android.os.Parcelable
import java.util.Date

data class StructureRecurringTasks(
    val taskDescription: String = "",
    val startDate: StructureDateTime = StructureDateTime(),  // Current date/time
    val endDate: StructureDateTime = StructureDateTime(), // 7 days from now
    val frequency: Int = 24,  // Default 24 hours
    val status: Boolean = false  // Default as not completed
)