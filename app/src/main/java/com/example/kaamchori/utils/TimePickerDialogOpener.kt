package com.example.kaamchori.utils

import android.app.TimePickerDialog
import android.content.Context
import android.widget.Button
import com.example.kaamchori.models.StructureDateTime

fun TimePickerDialogOpener(
    context: Context,
    inpTime : StructureDateTime,
    inpButton : Button
) {
    val timePickerDialog = TimePickerDialog(
        context,
        {_,selectedHour,selectedMinute ->
            inpTime.hour = selectedHour
            inpTime.minute =selectedMinute
            inpButton.setText(getTimeString(inpTime))
        },inpTime.hour,inpTime.minute,true
    )
    timePickerDialog.show()
}


