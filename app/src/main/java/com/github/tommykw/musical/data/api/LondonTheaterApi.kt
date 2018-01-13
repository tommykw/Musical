package com.github.tommykw.musical.api

import retrofit2.http.GET
import io.reactivex.Observable
import com.github.tommykw.musical.data.entity.Event

interface LondonTheaterApi {
    companion object {
        val VERSION = "2.5"
    }
    @GET("/2.5/weather?q=Tokyo,jp")
    fun getEvent() : Observable<Event>
}