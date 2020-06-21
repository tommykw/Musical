package com.github.tommykw.musical.data.network

import com.github.tommykw.musical.data.entity.Musical
import retrofit2.http.GET

interface MusicalService {

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/"
    }

    @GET("tommykw/Musical/master/data/musicals.json")
    suspend fun getAllMusicals(): List<Musical>

    @GET("tommykw/Musical/master/data/favorites.json")
    suspend fun getFavoritesSortOrder() : List<Musical>
}