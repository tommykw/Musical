package com.github.tommykw.musical.data.repository

import com.github.tommykw.musical.api.ApiClient

class TheaterRepository {
    fun fetchEvent() = ApiClient.getApiClient().getEvent()

    fun fetchEvents() {
        TODO()
    }

    fun fetchVenues() {
        TODO()
    }

    fun fetchVenue() {
        TODO()
    }
}