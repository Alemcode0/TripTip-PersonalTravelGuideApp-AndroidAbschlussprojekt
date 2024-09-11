package com.example.abschlissprojekt.data.models

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    //für die Umwandlung einer Liste von Int-Werten in einen JSON-String
    @TypeConverter
    fun fromIntList(list: List<Int>?): String? {
        return if (list == null) null else Gson().toJson(list)
    }

    //für die Umwandlung eines JSON-Strings in eine Liste von Int-Werten
    @TypeConverter
    fun toIntList(json: String?): List<Int>? {
        return if (json == null) null else Gson().fromJson(json, object : TypeToken<List<Int>>() {}.type)
        // Wenn der JSON-String nicht null ist, wird er mithilfe von Gson in eine Liste von Int-Werten konvertiert.
        // Die TypeToken-Klasse wird verwendet, um den korrekten Typ für die Liste zu ermitteln.
    }

}