package com.example.kaamchori.utils

import com.example.kaamchori.models.StructureDateTime

fun getDateString(inpDate: StructureDateTime): String{
    var retString: String =  ""
    if (inpDate.day < 10) retString+="0"
    retString+=inpDate.day.toString()
    retString+="/"

    if (inpDate.month < 10) retString+="0"
    retString+=inpDate.month.toString()
    retString+="/"

    retString+=inpDate.year
    return retString
}