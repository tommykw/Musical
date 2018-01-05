package com.github.tommykw.musical.api

import retrofit2.http.GET
import com.github.tommykw.musical.entity.Weather
import io.reactivex.Observable

/**
 * Created by tommy on 15/10/07.
 */
interface ApiService {
    companion object {
        val VERSION = "2.5"
    }
    @GET("/2.5/weather?q=Tokyo,jp")
    fun getWeather() : Observable<Weather>
}