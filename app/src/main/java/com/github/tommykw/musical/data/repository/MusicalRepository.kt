package com.github.tommykw.musical.data.repository

import androidx.annotation.AnyThread
import com.github.tommykw.musical.data.entity.Musical
import com.github.tommykw.musical.data.local.MusicalDao
import com.github.tommykw.musical.data.network.MusicalRemoteDataSource
import com.github.tommykw.musical.utils.ComparablePair
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicalRepository @Inject constructor(
        private val musicalDao: MusicalDao,
        private val musicalRDS: MusicalRemoteDataSource,
        private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    private var favoriteSortOrder: List<Int> = listOf()

    private val favoriteFlow = flow {
        favoriteSortOrder = musicalRDS.favoritesSortOrder()
        emit(favoriteSortOrder)
    }

    val musicalFlow: Flow<List<Musical>>
        get() = musicalDao.loadAllMusicalsFlow()
            .combine(favoriteFlow) { musicals, favoritesOrder ->
                musicals.applySort(favoritesOrder)
            }
            .flowOn(defaultDispatcher)
            .conflate()

    fun getMusicalsForIdFlow(): Flow<List<Musical>> {
        return musicalDao.getMusicalsForIdFlow()
            .map { musicalList ->
                musicalList.applyMainSafeSort(favoriteSortOrder)
            }
    }

    private fun shouldUpdateMusicalsCache(): Boolean {
        return true
    }

    suspend fun tryUpdateRecentMusicalsCache() {
        if (shouldUpdateMusicalsCache()) fetchRecentMusicals()
    }

    suspend fun tryUpdateRecentMusicalsForIdCache() {
        if (shouldUpdateMusicalsCache()) fetchRecentMusicalsForId()
    }

    private suspend fun fetchRecentMusicals() {
        val musicals = musicalRDS.fetchAllMusicals()
        musicalDao.saveAll(musicals)
    }

    private suspend fun fetchRecentMusicalsForId() {
        val musicalForId = musicalRDS.fetchAllMusicals()
        musicalDao.saveAll(musicalForId)
    }

    private fun List<Musical>.applySort(favoritesSortOrder: List<Int>): List<Musical> {
        return sortedBy { musical ->
            val positionForItem = favoritesSortOrder.indexOf(musical.id).let { order ->
                if (order > -1) order else Int.MAX_VALUE
            }
            ComparablePair(positionForItem, musical.id)
        }
    }

    @AnyThread
    suspend fun List<Musical>.applyMainSafeSort(favoritesSortOrder: List<Int>) =
            withContext(defaultDispatcher) {
                this@applyMainSafeSort.applySort(favoritesSortOrder)
            }
}