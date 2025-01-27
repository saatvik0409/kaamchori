package com.example.kaamchori.utils

import com.example.kaamchori.models.StructureDateTime

fun compareDateTime (
    startDateTime : StructureDateTime,
    endDateTime: StructureDateTime
) : Boolean {
    if (startDateTime.year > endDateTime.year) return false
    else if (startDateTime.year < endDateTime.year) return true

    if (startDateTime.month > endDateTime.month) return false
    else if (startDateTime.month < endDateTime.month) return true

    if (startDateTime.day > endDateTime.day) return false
    else if (startDateTime.day < endDateTime.day) return true

    if (startDateTime.hour > endDateTime.hour) return false
    else if (startDateTime.hour < endDateTime.hour) return true

    if (startDateTime.minute > endDateTime.minute) return false
    else if (startDateTime.minute < endDateTime.minute) return true

    return false
}