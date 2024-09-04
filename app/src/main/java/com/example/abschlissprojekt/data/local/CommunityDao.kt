package com.example.abschlissprojekt.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.abschlissprojekt.data.models.Community

@Dao
interface CommunityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(community: Community)

    @Update
    suspend fun update(community: Community)

    @Delete
    suspend fun delete(community: Community)

    @Query("SELECT * FROM community_table")
    fun getAllCommunities(): LiveData<List<Community>>

    @Query("SELECT * FROM community_table WHERE id = :id")
    fun getCommunityById(id: Long): LiveData<Community>

}