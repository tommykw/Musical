package com.github.tommykw.musical.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Episode(
        @PrimaryKey val episodeId: String,
        val name: String,
        val director: String,
        val number: Float,
        val poster: String,
        val trilogy: Int
)

inline class Trilogy(val number: Int)
val NoTrilogy = Trilogy(-1)