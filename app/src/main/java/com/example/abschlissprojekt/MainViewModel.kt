package com.example.abschlissprojekt

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.abschlissprojekt.data.DestinationRepository
import com.example.abschlissprojekt.data.local.DestinationDatabase.Companion.getDatabase
import com.example.abschlissprojekt.data.models.Destination
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private var repository = DestinationRepository(database)

    var destinationList = repository.allDestinations

    init {
        //resetDatabase()
        //val destinationDao = getDatabase(application).destinationDao
        repository = DestinationRepository(database)
        destinationList = repository.allDestinations
        insert()
    }

    fun insert() =
        viewModelScope.launch {
            repository.insert()
        }

    fun resetDatabase() =
        viewModelScope.launch {
            repository.resetDb()
        }
}