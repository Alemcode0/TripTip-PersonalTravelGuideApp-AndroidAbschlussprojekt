package com.example.abschlissprojekt.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.abschlissprojekt.data.models.Community
import com.example.abschlissprojekt.data.models.Converter
import com.example.abschlissprojekt.data.models.LatLngConverter

@Database(entities = [Community::class], version = 1)
@TypeConverters(Converter::class, LatLngConverter::class)
abstract class CommunityDatabase : RoomDatabase() {
    abstract fun communityDao(): CommunityDao

    companion object {
        private lateinit var instance: CommunityDatabase

        fun getDatabase(context: Context): CommunityDatabase {
            synchronized(CommunityDatabase::class.java) {
                if (!this::instance.isInitialized) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CommunityDatabase::class.java,
                        "community_database1"
                    ).build()
                }
                return instance
            }
        }
    }
}