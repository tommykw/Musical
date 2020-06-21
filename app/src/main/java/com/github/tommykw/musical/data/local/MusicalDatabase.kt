package com.github.tommykw.musical.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.tommykw.musical.data.entity.Musical

@Database(entities = [Musical::class], version = 1, exportSchema = false)
abstract class MusicalDatabase : RoomDatabase() {
    abstract fun musicalDao(): MusicalDao

    companion object {

        @Volatile
        private var instance: MusicalDatabase? = null

        fun getInstance(context: Context): MusicalDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): MusicalDatabase {
            return Room.databaseBuilder(context, MusicalDatabase::class.java, "musicaldb")
                    .build()
        }
    }
}