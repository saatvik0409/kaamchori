package com.example.kaamchori.utils

import com.example.kaamchori.models.StructureDateTime

fun getTimeString (inpTime: StructureDateTime): String{

    var ret_string : String = ""
    if (inpTime.hour < 10) ret_string+="0"
    ret_string+=inpTime.hour.toString()

    ret_string+=":"

    if (inpTime.minute < 10) ret_string+="0"
    ret_string+=inpTime.minute.toString()

    return ret_string
}