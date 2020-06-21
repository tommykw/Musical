package com.github.tommykw.musical.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Musical(
        @PrimaryKey val id: Int,
        val productionId: String,
        val name: String,
        val playwright: String,
        val poster: String
)

//inline class Trilogy(val number: Int)
//val NoTrilogy = Trilogy(-1)