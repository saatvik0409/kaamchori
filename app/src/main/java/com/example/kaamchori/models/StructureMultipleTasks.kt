package com.example.kaamchori.models

import java.util.Date

data class StructureMultipleTasks (
    var id : Long = -1,
    var taskDescription: String = "",
    var startDate: StructureDateTime = StructureDateTime(),  // Current date/time
    var endDate: StructureDateTime = StructureDateTime(), // 7 days from now
    var status: Int = 0,  // Default as not completed,
    var totalQty : Int = 0,
)