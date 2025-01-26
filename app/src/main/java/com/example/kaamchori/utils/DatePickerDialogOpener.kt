package com.example.kaamchori.utils

import android.app.DatePickerDialog
import android.content.Context
import android.widget.Button
import com.example.kaamchori.models.StructureDateTime
import java.time.Month
import java.time.Year
import java.util.Calendar

fun DatePickerDialogOpener(
    context: Context,
    inputDateTime: StructureDateTime,
    inpButton: Button
    ) {

    val datePickerDialog = DatePickerDialog(
        context,
        {_,selectedYear,selectedMonth,selectedDay ->
            inputDateTime.year=selectedYear
            inputDateTime.month=selectedMonth+1
            inputDateTime.day = selectedDay
            inpButton.setText(getDateString(inputDateTime))
        }, inputDateTime.year,inputDateTime.month-1,inputDateTime.day
    )
    datePickerDialog.show()
}