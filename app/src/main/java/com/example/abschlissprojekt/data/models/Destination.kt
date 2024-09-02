package com.example.abschlissprojekt.data.models

import android.provider.MediaStore.Images
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "destination_table")

data class Destination(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val imageUrl: Int,
    val imageUrl1: Int,
    val imageUrl2: Int,
    val imageUrl3: Int,
    val imageUrl4: Int,
    val location: String,
    var favourite: Boolean = true
)
