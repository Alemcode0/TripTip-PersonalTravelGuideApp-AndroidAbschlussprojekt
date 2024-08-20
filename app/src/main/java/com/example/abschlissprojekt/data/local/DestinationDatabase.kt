package com.example.abschlissprojekt.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.abschlissprojekt.data.models.Destination

@Database(entities = [Destination::class], version = 1)
abstract class DestinationDatabase: RoomDatabase() {

    abstract val destinationDao: DestinationDao

    companion object {
        private lateinit var instance: DestinationDatabase

        fun getDatabase(context: Context): DestinationDatabase {
            synchronized(DestinationDatabase::class.java) {
                if (!this::instance.isInitialized) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DestinationDatabase::class.java,
                        "destination_database1"
                    ).build()
                }
                return instance
            }
        }
    }
}