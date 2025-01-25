package com.example.kaamchori.database

import android.content.Context
import com.example.kaamchori.models.StructureMultipleTasks
import com.example.kaamchori.models.StructureOneTimeTasks
import com.example.kaamchori.models.StructureRecurringTasks

class DatabaseManager(context: Context) {

    private val dbHelper: DatabaseHelper = DatabaseHelper(context)

    fun insertOneTimeTask (oneTimeTask: StructureOneTimeTasks){
        //ImplementThis
    }

    fun insertMultipleTask (multipleTask : StructureMultipleTasks){
        //Implement THis
    }

    fun insertRecurringTask (recurringTask: StructureRecurringTasks){
        //Implement THis
    }

    fun updateOneTimeTask (oneTimeTask: StructureOneTimeTasks){
        //Implement this
    }

    fun updateMultipleTask (multipleTask: StructureMultipleTasks){
        //Implement this
    }

    fun updateRecurringTask (recurringTask : StructureRecurringTasks){
        //Implement this
    }

    fun fetchAllData (){
        //Implement This
    }


}