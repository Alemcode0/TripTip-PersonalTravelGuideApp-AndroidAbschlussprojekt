package com.example.abschlissprojekt.data.models

import androidx.room.TypeConverter
import com.google.android.gms.maps.model.LatLng

class LatLngConverter {

    @TypeConverter
    fun fromLatLng(latLng: LatLng?): String? {
        return if (latLng == null) {
            null
        } else {
            "${latLng.latitude},${latLng.longitude}"
        }
    }

    @TypeConverter
    fun toLatLng(latLngString: String?): LatLng? {
        return if (latLngString == null) {
            null
        } else {
            val parts = latLngString.split(",")
            LatLng(parts[0].toDouble(), parts[1].toDouble())
        }
    }
}