package com.github.tommykw.musical.data

import io.reactivex.Observable

interface TheaterRepository {
    fun loadEvents(): Observable<List<Any>>
    fun loadVenues(): Observable<List<Any>>
    fun loadEvent(): Observable<Any>
    fun loadVenue(): Observable<Any>
}