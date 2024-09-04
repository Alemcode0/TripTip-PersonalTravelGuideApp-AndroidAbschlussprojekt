package com.example.abschlissprojekt.data.models

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromIntList(list: List<Int>?): String? {
        return if (list == null) null else Gson().toJson(list)
    }

    @TypeConverter
    fun toIntList(json: String?): List<Int>? {
        return if (json == null) null else Gson().fromJson(json, object : TypeToken<List<Int>>() {}.type)
    }
}