package com.example.abschlissprojekt

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.abschlissprojekt.data.DestinationRepository
import com.example.abschlissprojekt.data.local.DestinationDao
import com.example.abschlissprojekt.data.local.DestinationDatabase
import com.example.abschlissprojekt.data.local.DestinationDatabase.Companion.getDatabase
import com.example.abschlissprojekt.data.models.Destination
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private var repository = DestinationRepository(database)
    private val destinationDao: DestinationDao = getDatabase(application).destinationDao
    val favouriteDestinations: LiveData<List<Destination>>

    var destinationList = repository.allDestinations

    private val _allFavourites = MutableLiveData<List<Destination>>()
    val allFavourites: LiveData<List<Destination>> get() = _allFavourites

    init {
        repository = DestinationRepository(database)
        destinationList = repository.allDestinations
        insert()
        loadFavourites()
        favouriteDestinations = repository.getFavourites()
    }

    fun insert() =
        viewModelScope.launch {
            repository.insert()
        }

    fun resetDatabase() =
        viewModelScope.launch {
            repository.resetDb()
        }

    private fun loadFavourites() {
        viewModelScope.launch {
            _allFavourites.postValue(repository.getAllFavourites())
        }
    }

    fun addFavourite(destination: Destination) {
        viewModelScope.launch {
            repository.addFavourite(destination)
            loadFavourites() // Refresh list
        }
    }

    fun removeFavourite(destination: Destination) {
        viewModelScope.launch {
            repository.removeFavourite(destination)
            loadFavourites() // Refresh list
        }
    }

    fun searchDestinations(query: String): LiveData<List<Destination>> {
        val filteredDestinations = destinationList.value?.filter {
            it.name.contains(query, ignoreCase = true)
        }
        return MutableLiveData(filteredDestinations)
    }

    fun updateDestination(destination: Destination) {
        viewModelScope.launch {
            repository.update(destination)
        }
    }

    fun getAllDestinations() {
        viewModelScope.launch {
            repository.allDestinations
        }
    }
}