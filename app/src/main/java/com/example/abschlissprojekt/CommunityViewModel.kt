package com.example.abschlissprojekt

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.abschlissprojekt.data.CommunityRepository
import com.example.abschlissprojekt.data.local.CommunityDao
import com.example.abschlissprojekt.data.local.CommunityDatabase
import com.example.abschlissprojekt.data.models.Community
import kotlinx.coroutines.launch

class CommunityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CommunityRepository
    val allCommunities: LiveData<List<Community>>

    init {
        // Holen des DAO (Data Access Object) für die Community-Datenbank
        val communityDao = CommunityDatabase.getDatabase(application).communityDao()
        // Initialisierung des Repositories mit dem DAO, um Datenbankzugriffe zu verwalten
        repository = CommunityRepository(communityDao)
        allCommunities = repository.getAllCommunities()
        insert()
    }

    fun getCommunityById(id: Long): LiveData<Community> {
        return repository.getCommunityById(id)
    }

    fun insert() =
        viewModelScope.launch {
            repository.insert()
        }


    fun update(community: Community) =
        // Startet eine Coroutine, um den Update-Vorgang asynchron durchzuführen
        viewModelScope.launch {
        repository.update(community)
    }

    fun delete(community: Community) =
        viewModelScope.launch {
        repository.delete(community)
    }
}