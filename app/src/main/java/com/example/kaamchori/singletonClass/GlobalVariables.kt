package com.example.kaamchori.singletonClass

import com.example.kaamchori.models.StructureOneTimeTasks
import com.example.kaamchori.models.StructureRecurringTasks

object GlobalVariables {
    var recurringTasksList = mutableListOf<StructureRecurringTasks>()
    var oneTimeTasksList = mutableListOf<StructureOneTimeTasks>()
}