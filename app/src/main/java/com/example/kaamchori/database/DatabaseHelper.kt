package com.example.kaamchori.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME, null, DATABASE_VERSION){

    companion object {
        private const val DATABASE_NAME = "tasks_data.db"
        private const val DATABASE_VERSION = 1

        private const val CREATE_TABLE_ONE_TIME_TASKS = """
            CREATE TABLE oneTimeTasks (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                description TEXT,
                startDateTime DATETIME,
                endDateTime DATETIME,
                status BOOL
            )
        """

        private const val CREATE_TABLE_MULTIPLE_TASKS = """
            CREATE TABLE multipleTasks (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                description TEXT,
                startDateTime DATETIME,
                endDateTime DATETIME,
                completed INTEGER,
                total INTEGER
            )
        """

        private const val CREATE_TABLE_RECURRING_TASKS = """
            CREATE TABLE recurringTasks(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                description TEXT,
                startDateTime DATETIME,
                endDateTime DATETIME,
                frequency INTEGER,
                status BOOL
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