package com.example.kaamchori.models

import android.app.ActivityManager.TaskDescription
import android.os.Parcel
import android.os.Parcelable
import java.util.Date

data class StructureRecurringTasks(
    val taskDescription: String = "",
    val startDate: Date = Date(),  // Current date/time
    val endDate: Date = Date(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000)), // 7 days from now
    val frequency: Int = 24,  // Default 24 hours
    val status: Boolean = false  // Default as not completed
)