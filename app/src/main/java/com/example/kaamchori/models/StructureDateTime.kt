package com.example.kaamchori.models

import java.util.Calendar

data class StructureDateTime(
    var year : Int = Calendar.getInstance().get(Calendar.YEAR),
    var month : Int = Calendar.getInstance().get(Calendar.MONTH)+1,
    var day : Int = Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
    var hour : Int = 0,
    var minute : Int = 0,
    var second : Int = 0
)