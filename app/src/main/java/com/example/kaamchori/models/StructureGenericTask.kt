package com.example.kaamchori.models

data class StructureGenericTask (
    /*
     0 -> one time 1 -> recurring 2 -> multiple
     */
    var type : Int = -1,
    var recurringTask : Int = 0,
    var multipleTask : Int = 0,
    var oneTimeTask : Int = 0,
    var taskTitle : String = "",
    var startDateTime : StructureDateTime = StructureDateTime(),
    var taskDeadline : StructureDateTime = StructureDateTime()
)
