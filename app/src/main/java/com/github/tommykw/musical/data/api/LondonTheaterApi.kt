package com.github.tommykw.musical.api

import retrofit2.http.GET
import com.github.tommykw.musical.data.entity.Event
import kotlinx.coroutines.flow.Flow

interface LondonTheaterApi {
    companion object {
        val VERSION = "2.5"
    }

    @GET("/2.5/weather?q=Tokyo,jp")
    suspend fun getEvent() : Flow<Event>
}