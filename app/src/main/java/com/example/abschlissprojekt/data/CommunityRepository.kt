package com.example.abschlissprojekt.data

import androidx.lifecycle.LiveData
import com.example.abschlissprojekt.data.exampleData.CommunityExampleData
import com.example.abschlissprojekt.data.local.CommunityDao
import com.example.abschlissprojekt.data.models.Community

class CommunityRepository(private val communityDao: CommunityDao) {

    fun getAllCommunities(): LiveData<List<Community>> {
        return communityDao.getAllCommunities()
    }

    fun getCommunityById(id: Long): LiveData<Community> {
        return communityDao.getCommunityById(id)
    }

    suspend fun insert() {
        communityDao.insert(CommunityExampleData.Contact1)
        communityDao.insert(CommunityExampleData.Contact2)
        communityDao.insert(CommunityExampleData.Contact3)
        communityDao.insert(CommunityExampleData.Contact4)
        communityDao.insert(CommunityExampleData.Contact5)
        communityDao.insert(CommunityExampleData.Contact6)
        communityDao.insert(CommunityExampleData.Contact7)
    }

    suspend fun update(community: Community) {
        communityDao.update(community)
    }

    suspend fun delete(community: Community) {
        communityDao.delete(community)
    }
}