package com.github.tommykw.musical.data.network

import com.github.tommykw.musical.data.entity.Episode
import retrofit2.http.GET

interface EpisodeService {

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/"
    }

    @GET("orionthewake/episodes-flow/master/data/episodes.json")
    suspend fun getAllEpisodes(): List<Episode>

    @GET("orionthewake/episodes-flow/master/data/favorites.json")
    suspend fun getFavoritesSortOrder() : List<Episode>
}