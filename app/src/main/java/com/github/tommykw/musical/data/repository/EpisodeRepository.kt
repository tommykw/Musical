package com.github.tommykw.musical.data.repository

import androidx.annotation.AnyThread
import com.github.tommykw.musical.data.entity.Episode
import com.github.tommykw.musical.data.entity.Trilogy
import com.github.tommykw.musical.data.local.EpisodeDao
import com.github.tommykw.musical.data.network.EpisodeRemoteDataSource
import com.github.tommykw.musical.utils.ComparablePair
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EpisodeRepository @Inject constructor(
    private val episodeDao: EpisodeDao,
    private val episodeRDS: EpisodeRemoteDataSource,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    private var favoriteSortOrder: List<String> = listOf()

    private val favoriteFlow = flow {
        favoriteSortOrder = episodeRDS.favoritesSortOrder()
        emit(favoriteSortOrder)
    }

    val episodeFlow: Flow<List<Episode>>
        get() = episodeDao.loadAllEpisodesFlow()
            .combine(favoriteFlow) { episodes, favoritesOrder ->
                episodes.applySort(favoritesOrder)
            }
            .flowOn(defaultDispatcher)
            .conflate()

    fun getEpisodesForTrilogyFlow(trilogy: Trilogy): Flow<List<Episode>> {
        return episodeDao.getEpisodesForTrilogyNumberFlow(trilogy.number)
            .map { episodeList ->
                episodeList.applyMainSafeSort(favoriteSortOrder)
            }
    }

    private fun shouldUpdateEpisodesCache(): Boolean {
        return true
    }

    suspend fun tryUpdateRecentEpisodesCache() {
        if (shouldUpdateEpisodesCache()) fetchRecentEpisodes()
    }

    suspend fun tryUpdateRecentEpisodesForTrilogyCache(trilogy: Trilogy) {
        if (shouldUpdateEpisodesCache()) fetchRecentEpisodesForTrilogy(trilogy)
    }

    private suspend fun fetchRecentEpisodes() {
        val episodes = episodeRDS.fetchAllEpisodes()
        episodeDao.saveAll(episodes)
    }

    private suspend fun fetchRecentEpisodesForTrilogy(trilogy: Trilogy) {
        val episodesForTrilogy = episodeRDS.episodesForTrilogy(trilogy)
        episodeDao.saveAll(episodesForTrilogy)
    }

    private fun List<Episode>.applySort(favoritesSortOrder: List<String>): List<Episode> {
        return sortedBy { episode ->
            val positionForItem = favoritesSortOrder.indexOf(episode.episodeId).let { order ->
                if (order > -1) order else Int.MAX_VALUE
            }
            ComparablePair(positionForItem, episode.number)
        }
    }

    @AnyThread
    suspend fun List<Episode>.applyMainSafeSort(favoritesSortOrder: List<String>) =
            withContext(defaultDispatcher) {
                this@applyMainSafeSort.applySort(favoritesSortOrder)
            }
}