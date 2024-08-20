package com.example.abschlissprojekt.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.abschlissprojekt.data.exampleData.DestinationExampleData
import com.example.abschlissprojekt.data.local.DestinationDatabase
import com.example.abschlissprojekt.data.models.Destination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class DestinationRepository(private val database: DestinationDatabase) {

    val allDestinations: LiveData<List<Destination>> = database.destinationDao.getAllDestinations()

    suspend fun insert() {
        try {
            database.destinationDao.insert(DestinationExampleData.Destination1)
            database.destinationDao.insert(DestinationExampleData.Destination2)
            database.destinationDao.insert(DestinationExampleData.Destination3)
            database.destinationDao.insert(DestinationExampleData.Destination4)
            database.destinationDao.insert(DestinationExampleData.Destination5)
            database.destinationDao.insert(DestinationExampleData.Destination6)
            database.destinationDao.insert(DestinationExampleData.Destination7)
            database.destinationDao.insert(DestinationExampleData.Destination8)
            database.destinationDao.insert(DestinationExampleData.Destination9)
            database.destinationDao.insert(DestinationExampleData.Destination10)
        } catch (e: Exception) {
            Log.d("Repository", "Failed to insert into Database: $e")
        }
    }

    suspend fun resetDb() = withContext(Dispatchers.IO){
        database.runInTransaction{
            runBlocking {
                database.clearAllTables()
                database.destinationDao.deletePrimaryKeyIndex()
            }
        }
    }
}