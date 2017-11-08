package com.github.tommykw.domain.repository

import rx.Observable

interface TheaterRepository {
    fun loadEvents(): Observable<List<Any>>
    fun loadVenues(): Observable<List<Any>>
    fun loadEvent(): Observable<Any>
    fun loadVenue(): Observable<Any>
}