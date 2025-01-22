package com.example.kaamchori.models

import java.util.Date

data class StructureOneTimeTasks (
    val taskDescription: String = "",
    val startDate: Date = Date(),  // Current date/time
    val endDate: Date = Date(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000)), // 7 days from now
    val status: Boolean = false  // Default as not completed
)