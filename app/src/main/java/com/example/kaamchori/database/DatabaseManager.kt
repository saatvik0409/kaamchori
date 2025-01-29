package com.example.kaamchori.database

import android.R.attr.name
import android.content.ContentValues
import android.content.Context
import com.example.kaamchori.models.StructureDateTime
import com.example.kaamchori.models.StructureMultipleTasks
import com.example.kaamchori.models.StructureOneTimeTasks
import com.example.kaamchori.models.StructureRecurringTasks
import com.example.kaamchori.singletonClass.DBNames
import com.example.kaamchori.singletonClass.GlobalVariables
import com.example.kaamchori.utils.getDateTimeString
import com.example.kaamchori.utils.getStructureDateTimeFromString
import java.sql.Struct


class DatabaseManager(context: Context) {

    private val dbHelper: DatabaseHelper = DatabaseHelper(context)

    fun insertOneTimeTask (oneTimeTask: StructureOneTimeTasks){
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(DBNames.FIELD_ONE_TIME_DESCRIPTION,oneTimeTask.taskDescription)
            put(DBNames.FIELD_ONE_TIME_START_DATE_TIME, getDateTimeString(oneTimeTask.startDate))
            put(DBNames.FIELD_ONE_TIME_END_DATE_TIME, getDateTimeString(oneTimeTask.endDate))
            put(DBNames.FIELD_ONE_TIME_STATUS,oneTimeTask.status)
        }

        oneTimeTask.id = db.insert(DBNames.TB_NAME_ONE_TIME_TASKS, null, values)
        db.close()
    }

    fun insertMultipleTask (multipleTask : StructureMultipleTasks){
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(DBNames.FIELD_MULTIPLE_DESCRIPTION,multipleTask.taskDescription)
            put(DBNames.FIELD_MULTIPLE_START_DATE_TIME, getDateTimeString(multipleTask.startDate))
            put(DBNames.FIELD_MULTIPLE_END_DATE_TIME, getDateTimeString(multipleTask.endDate))
            put(DBNames.FIELD_MULTIPLE_COMPLETED,multipleTask.status)
            put(DBNames.FIELD_MULTIPLE_TOTAL,multipleTask.totalQty)
        }

        multipleTask.id = db.insert(DBNames.TB_NAME_MULTIPLE_TASKS, null, values)
        db.close()
    }

    fun insertRecurringTask (recurringTask: StructureRecurringTasks){
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(DBNames.FIELD_RECURRING_DESCRIPTION,recurringTask.taskDescription)
            put(DBNames.FIELD_RECURRING_START_DATE_TIME, getDateTimeString(recurringTask.startDate))
            put(DBNames.FIELD_RECURRING_END_DATE_TIME, getDateTimeString(recurringTask.endDate))
            put(DBNames.FIELD_RECURRING_FREQUENCY,recurringTask.status)
            put(DBNames.FIELD_RECURRING_STATUS,recurringTask.frequency)
        }

        recurringTask.id = db.insert(DBNames.TB_NAME_RECURRING_TASKS, null, values)
        db.close()
    }

    fun readRecurringTasks(){
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM ${DBNames.TB_NAME_RECURRING_TASKS}",
            null)

        while (cursor.moveToNext()) {
            val id : Long = cursor.getLong(0)
            val description : String = cursor.getString(1)
            val startDateTime : StructureDateTime = getStructureDateTimeFromString(cursor.getString(2))
            val endDateTime : StructureDateTime = getStructureDateTimeFromString(cursor.getString(3))
            val frequency : Int = cursor.getInt(4)
            val status : Boolean = cursor.getInt(5)==1

            val task = StructureRecurringTasks(
                id,
                description,
                startDateTime,
                endDateTime,
                frequency,
                status
            )

            GlobalVariables.recurringTasksList.add(task)
        }

        cursor.close()
        db.close()
    }

    fun readMultipleTasks (){
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM ${DBNames.TB_NAME_MULTIPLE_TASKS}",
            null)

        while (cursor.moveToNext()) {
            val id : Long =  cursor.getLong(0)
            val description : String = cursor.getString(1)
            val startDateTime : StructureDateTime = getStructureDateTimeFromString(cursor.getString(2))
            val endDateTime : StructureDateTime = getStructureDateTimeFromString(cursor.getString(3))
            val completed : Int = cursor.getInt(4)
            val total : Int = cursor.getInt(5)

            val task = StructureMultipleTasks(
                id,
                description,
                startDateTime,
                endDateTime,
                completed,
                total
            )

            GlobalVariables.multipleTasksList.add(task)
        }

        cursor.close()
        db.close()
    }

    fun readOneTimeTasks (){
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM ${DBNames.TB_NAME_ONE_TIME_TASKS}",
            null)

        while (cursor.moveToNext()) {
            val id : Long= cursor.getLong(0)
            val description : String = cursor.getString(1)
            val startDateTime : StructureDateTime = getStructureDateTimeFromString(cursor.getString(2))
            val endDateTime : StructureDateTime = getStructureDateTimeFromString(cursor.getString(3))
            val status : Boolean = cursor.getInt(4)==1

            val task = StructureOneTimeTasks(
                id,
                description,
                startDateTime,
                endDateTime,
                status
            )

            GlobalVariables.oneTimeTasksList.add(task)
        }

        cursor.close()
        db.close()
    }

    //fun updateEvent(db: SQLiteDatabase, id: Int, newName: String, newDate: Date): Int {
    fun updateOneTimeTask (oneTimeTask: StructureOneTimeTasks){
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DBNames.FIELD_ID,oneTimeTask.id)
            put(DBNames.FIELD_ONE_TIME_DESCRIPTION, oneTimeTask.taskDescription)
            put(DBNames.FIELD_ONE_TIME_START_DATE_TIME, getDateTimeString(oneTimeTask.startDate))
            put(DBNames.FIELD_ONE_TIME_END_DATE_TIME, getDateTimeString(oneTimeTask.endDate))
            put(DBNames.FIELD_ONE_TIME_STATUS,oneTimeTask.status)
        }

        db.update(DBNames.TB_NAME_ONE_TIME_TASKS, values, "id = ?", arrayOf(oneTimeTask.id.toString()))
        return
    }

    fun updateMultipleTask (multipleTask: StructureMultipleTasks){
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DBNames.FIELD_ID,multipleTask.id)
            put(DBNames.FIELD_MULTIPLE_DESCRIPTION, multipleTask.taskDescription)
            put(DBNames.FIELD_MULTIPLE_START_DATE_TIME, getDateTimeString(multipleTask.startDate))
            put(DBNames.FIELD_MULTIPLE_END_DATE_TIME, getDateTimeString(multipleTask.endDate))
            put(DBNames.FIELD_MULTIPLE_COMPLETED,multipleTask.status)
            put(DBNames.FIELD_MULTIPLE_TOTAL,multipleTask.totalQty)
        }

        db.update(DBNames.TB_NAME_MULTIPLE_TASKS, values, "id = ?", arrayOf(multipleTask.id.toString()))
    }

    fun updateRecurringTask (recurringTask : StructureRecurringTasks){
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DBNames.FIELD_ID,recurringTask.id)
            put(DBNames.FIELD_RECURRING_DESCRIPTION, recurringTask.taskDescription)
            put(DBNames.FIELD_RECURRING_START_DATE_TIME, getDateTimeString(recurringTask.startDate))
            put(DBNames.FIELD_RECURRING_END_DATE_TIME, getDateTimeString(recurringTask.endDate))
            put(DBNames.FIELD_RECURRING_FREQUENCY,recurringTask.frequency)
            put(DBNames.FIELD_ONE_TIME_STATUS,recurringTask.status)
        }

        db.update(DBNames.TB_NAME_RECURRING_TASKS, values, "id = ?", arrayOf(recurringTask.id.toString()))
    }

    fun fetchAllData (){
        readMultipleTasks()
        readRecurringTasks()
        readOneTimeTasks()
    }


}