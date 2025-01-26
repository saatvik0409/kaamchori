package com.example.kaamchori.models

import java.util.Date

data class StructureOneTimeTasks (
    val taskDescription: String = "",
    val startDate: StructureDateTime = StructureDateTime(),  // Current date/time
    val endDate: StructureDateTime = StructureDateTime(), // 7 days from now
    val status: Boolean = false  // Default as not completed
)