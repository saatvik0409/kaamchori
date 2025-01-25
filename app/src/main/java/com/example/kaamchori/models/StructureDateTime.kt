package com.example.kaamchori.models

data class StructureDateTime(
    val year : Int,
    val month : Int,
    val day : Int,
    val hour : Int = 0,
    val minute : Int = 0,
    val second : Int = 0
)