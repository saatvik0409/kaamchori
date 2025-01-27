package com.example.kaamchori.models

import android.app.ActivityManager.TaskDescription
import android.os.Parcel
import android.os.Parcelable
import java.util.Date

data class StructureRecurringTasks(
    var taskDescription: String = "",
    var startDate: StructureDateTime = StructureDateTime(),  // Current date/time
    var endDate: StructureDateTime = StructureDateTime(), // 7 days from now
    var frequency: Int = 24,  // Default 24 hours
    var status: Boolean = false  // Default as not completed
)