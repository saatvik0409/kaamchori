package com.example.kaamchori.models

import java.util.Calendar

data class StructureDateTime(
    var year : Int = Calendar.getInstance().get(Calendar.YEAR),
    var month : Int = Calendar.getInstance().get(Calendar.MONTH)+1,
    var day : Int = Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
    var hour : Int = Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
    var minute : Int = Calendar.getInstance().get(Calendar.MINUTE)
)