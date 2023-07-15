package com.dgarzona16.securepass.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromString(value: String): List<String> = value.split(",").map { it }

    @TypeConverter
    fun toString(value: MutableList<String>): String = value.joinToString(",")
}
