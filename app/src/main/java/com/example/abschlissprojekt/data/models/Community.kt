package com.example.abschlissprojekt.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "community_table")
data class Community(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val number: String,
    val image: Int,
    val imageStatus: List<Int>,
    val statusDescription: String
)
