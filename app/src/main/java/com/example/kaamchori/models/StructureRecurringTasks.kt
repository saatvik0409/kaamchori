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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        taskDescription = parcel.readString() ?: "",
        startDate = Date(parcel.readLong()),
        endDate = Date(parcel.readLong()),
        frequency = parcel.readInt(),
        status = parcel.readInt() == 1
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(taskDescription)
        parcel.writeLong(startDate.time)
        parcel.writeLong(endDate.time)
        parcel.writeInt(frequency)
        parcel.writeInt(if (status) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StructureRecurringTasks> {
        override fun createFromParcel(parcel: Parcel): StructureRecurringTasks {
            return StructureRecurringTasks(parcel)
        }

        override fun newArray(size: Int): Array<StructureRecurringTasks?> {
            return arrayOfNulls(size)
        }
    }
}