package com.github.tommykw.musical.data.network

import com.github.tommykw.musical.data.entity.Trilogy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EpisodeRemoteDataSource @Inject constructor(private val service: EpisodeService) {

    suspend fun fetchAllEpisodes() = withContext(Dispatchers.IO) {
        service.getAllEpisodes()
    }

    suspend fun episodesForTrilogy(trilogy: Trilogy) = withContext(Dispatchers.IO) {
        val result = service.getAllEpisodes()
        result.filter { it.trilogy == trilogy.number }
    }

    suspend fun favoritesSortOrder(): List<String> = withContext(Dispatchers.IO) {
        val result = service.getFavoritesSortOrder()
        result.map { episode -> episode.episodeId }
    }
}