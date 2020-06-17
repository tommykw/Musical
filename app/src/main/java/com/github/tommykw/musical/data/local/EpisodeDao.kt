package com.github.tommykw.musical.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.tommykw.musical.data.entity.Episode
import kotlinx.coroutines.flow.Flow

@Dao
interface EpisodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(episode: Episode)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(episodes: List<Episode>)

    @Query("SELECT * FROM episode ORDER BY number")
    fun loadAllEpisodesFlow(): Flow<List<Episode>>

    @Query("SELECT * FROM episode WHERE trilogy = :trilogyNumber ORDER BY number")
    fun getEpisodesForTrilogyNumberFlow(trilogyNumber: Int): Flow<List<Episode>>
}