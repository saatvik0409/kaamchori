package com.example.kaamchori.utils

import com.example.kaamchori.models.StructureDateTime

fun getDateTimeString(inpDateTime : StructureDateTime) : String{
    var inpString = ""
    inpString+= getDateString(inpDateTime)
    inpString+=" "
    inpString+= getTimeString(inpDateTime)
    return inpString
}