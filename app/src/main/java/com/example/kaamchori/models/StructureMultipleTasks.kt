package com.example.kaamchori.models

import java.util.Date

data class StructureMultipleTasks (
    val taskDescription: String = "",
    val startDate: Date = Date(),  // Current date/time
    val endDate: Date = Date(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000)), // 7 days from now
    val totalQty : Int = 0,
    val status: Int = 0  // Default as not completed
)