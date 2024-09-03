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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavourite(destination: Destination)

    @Delete
    suspend fun removeFavourite(destination: Destination)

    @Query("SELECT * FROM destination_table")
    suspend fun getAllFavourites(): List<Destination>

    @Query("SELECT * FROM destination_table WHERE name LIKE :searchQuery OR location LIKE :searchQuery")
    fun searchDestinations(searchQuery: String): LiveData<List<Destination>>

    @Query("SELECT * FROM destination_table WHERE favourite = 1")
    fun getFavourites(): LiveData<List<Destination>>

    @Update
    suspend fun update(destination: Destination)
}