package com.example.kaamchori.singletonClass

import com.example.kaamchori.database.DatabaseManager
import com.example.kaamchori.models.StructureGenericTask
import com.example.kaamchori.models.StructureMultipleTasks
import com.example.kaamchori.models.StructureOneTimeTasks
import com.example.kaamchori.models.StructureRecurringTasks

object GlobalVariables {
    var recurringTasksList = mutableListOf<StructureRecurringTasks>()
    var oneTimeTasksList = mutableListOf<StructureOneTimeTasks>()
    var multipleTasksList = mutableListOf<StructureMultipleTasks>()
    var queueTasksList = mutableListOf<StructureGenericTask>()


}

object DBNames {
    const val TB_NAME_ONE_TIME_TASKS = "ONE_TIME_TASKS"
    const val TB_NAME_RECURRING_TASKS = "RECURRING_TASKS"
    const val TB_NAME_MULTIPLE_TASKS = "MULTIPLE_TASKS"

    const val FIELD_ONE_TIME_DESCRIPTION = "DESCRIPTION"
    const val FIELD_ONE_TIME_START_DATE_TIME = "START_DATE_TIME"
    const val FIELD_ONE_TIME_END_DATE_TIME = "END_DATE_TIME"
    const val FIELD_ONE_TIME_STATUS = "STATUS"

    const val FIELD_RECURRING_DESCRIPTION = "DESCRIPTION"
    const val FIELD_RECURRING_START_DATE_TIME = "START_DATE_TIME"
    const val FIELD_RECURRING_END_DATE_TIME = "END_DATE_TIME"
    const val FIELD_RECURRING_FREQUENCY = "FREQUENCY"
    const val FIELD_RECURRING_STATUS = "STATUS"

    const val FIELD_MULTIPLE_DESCRIPTION = "DESCRIPTION"
    const val FIELD_MULTIPLE_START_DATE_TIME = "START_DATE_TIME"
    const val FIELD_MULTIPLE_END_DATE_TIME = "END_DATE_TIME"
    const val FIELD_MULTIPLE_COMPLETED = "COMPLETED"
    const val FIELD_MULTIPLE_TOTAL = "TOTAL"

    const val FIELD_ID = "ID"

}