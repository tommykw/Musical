package com.github.tommykw.musical.api

import retrofit2.http.GET
import io.reactivex.Observable
import tokyo.tommy_kw.musical.data.entity.Event

interface ApiService {
    companion object {
        val VERSION = "2.5"
    }
    @GET("/2.5/weather?q=Tokyo,jp")
    fun getEvent() : Observable<Event>
}