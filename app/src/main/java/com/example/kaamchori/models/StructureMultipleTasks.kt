package com.example.kaamchori.models

import java.util.Date

data class StructureMultipleTasks (
    val taskDescription: String = "",
    val startDate: StructureDateTime = StructureDateTime(),  // Current date/time
    val endDate: StructureDateTime = StructureDateTime(), // 7 days from now
    val totalQty : Int = 0,
    val status: Int = 0  // Default as not completed
)