package com.example.kaamchori.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.kaamchori.singletonClass.DBNames
import com.example.kaamchori.singletonClass.GlobalVariables

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME, null, DATABASE_VERSION){

    companion object {
        private const val DATABASE_NAME = "tasks_data.db"
        private const val DATABASE_VERSION = 1

        private const val CREATE_TABLE_ONE_TIME_TASKS = """
            CREATE TABLE ${DBNames.TB_NAME_ONE_TIME_TASKS} (
                ${DBNames.FIELD_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${DBNames.FIELD_ONE_TIME_DESCRIPTION}  TEXT,
                ${DBNames.FIELD_ONE_TIME_START_DATE_TIME}  TEXT,
                ${DBNames.FIELD_ONE_TIME_END_DATE_TIME}  TEXT,
                ${DBNames.FIELD_ONE_TIME_STATUS}  INTEGER
            )
        """

        private const val CREATE_TABLE_MULTIPLE_TASKS = """
            CREATE TABLE ${DBNames.TB_NAME_MULTIPLE_TASKS} (
                ${DBNames.FIELD_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${DBNames.FIELD_MULTIPLE_DESCRIPTION} TEXT,
                ${DBNames.FIELD_MULTIPLE_START_DATE_TIME} TEXT,
                ${DBNames.FIELD_MULTIPLE_END_DATE_TIME} TEXT,
                ${DBNames.FIELD_MULTIPLE_COMPLETED} INTEGER,
                ${DBNames.FIELD_MULTIPLE_TOTAL} INTEGER
            )
        """

        private const val CREATE_TABLE_RECURRING_TASKS = """
            CREATE TABLE ${DBNames.TB_NAME_RECURRING_TASKS}(
                ${DBNames.FIELD_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${DBNames.FIELD_RECURRING_DESCRIPTION} TEXT,
                ${DBNames.FIELD_RECURRING_START_DATE_TIME} TEXT,
                ${DBNames.FIELD_RECURRING_END_DATE_TIME} TEXT,
                ${DBNames.FIELD_RECURRING_FREQUENCY} INTEGER,
                ${DBNames.FIELD_RECURRING_STATUS} INTEGER
            )
        """


    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_ONE_TIME_TASKS)
        db?.execSQL(CREATE_TABLE_RECURRING_TASKS)
        db?.execSQL(CREATE_TABLE_MULTIPLE_TASKS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //WRITE LOGIC HERE IF NEEDED
    }
}