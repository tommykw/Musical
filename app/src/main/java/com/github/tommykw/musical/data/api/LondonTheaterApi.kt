package com.github.tommykw.musical.api

import retrofit2.http.GET
import com.github.tommykw.musical.data.entity.Event
import io.reactivex.Flowable

interface LondonTheaterApi {
    companion object {
        val VERSION = "2.5"
    }

    @GET("/2.5/weather?q=Tokyo,jp")
    fun getEvent() : Flowable<Event>
}