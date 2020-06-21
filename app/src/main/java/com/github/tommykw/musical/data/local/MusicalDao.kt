package com.github.tommykw.musical.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.tommykw.musical.data.entity.Musical
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(episode: Musical)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(episodes: List<Musical>)

    @Query("SELECT * FROM musical ORDER BY id")
    fun loadAllMusicalsFlow(): Flow<List<Musical>>

    //@Query("SELECT * FROM musical WHERE trilogy = :trilogyNumber ORDER BY number")
    @Query("SELECT * FROM musical ORDER BY id")
    fun getMusicalsForIdFlow(): Flow<List<Musical>>
}