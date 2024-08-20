package com.example.abschlissprojekt.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.abschlissprojekt.data.models.Destination

@Dao
interface DestinationDao {

    @Query("SELECT * FROM destination_table")
    fun getAllDestinations(): LiveData<List<Destination>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(destination: Destination)

    @Delete
    suspend fun delete(destination: Destination)

    @Query("DELETE FROM sqlite_sequence WHERE name = 'students'")
    suspend fun deletePrimaryKeyIndex()
}