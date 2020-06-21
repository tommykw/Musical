package com.github.tommykw.musical.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MusicalRemoteDataSource @Inject constructor(private val service: MusicalService) {

    suspend fun fetchAllEpisodes() = withContext(Dispatchers.IO) {
        service.getAllMusicals()
    }

    suspend fun favoritesSortOrder(): List<Int> = withContext(Dispatchers.IO) {
        val result = service.getFavoritesSortOrder()
        result.map { musical -> musical.id }
    }
}