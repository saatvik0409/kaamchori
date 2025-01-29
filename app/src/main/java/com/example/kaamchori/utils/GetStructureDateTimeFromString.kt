package com.example.kaamchori.utils

import com.example.kaamchori.models.StructureDateTime

fun getStructureDateTimeFromString (inpString: String) : StructureDateTime{

    //    DD/ MM / YYYY 00 : 00
    //    012 34 5 6789 12 3 45

    var retStructureDateTime = StructureDateTime()
    retStructureDateTime.day = inpString.substring(0,2).toInt()
    retStructureDateTime.month = inpString.substring(3,5).toInt()
    retStructureDateTime.year = inpString.substring(6,10).toInt()
    retStructureDateTime.hour = inpString.substring(11,13).toInt()
    retStructureDateTime.minute = inpString.substring(14,16).toInt()

    return retStructureDateTime
}