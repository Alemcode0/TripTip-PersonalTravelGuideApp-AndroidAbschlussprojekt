package com.example.abschlissprojekt.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "destination_table")

data class Destination(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val imageUrl: Int,
    val location: String
)
