package com.github.tommykw.data

data class Event(
    val eventId: Int,
    val eventType: Int,
    val name: String,
    val description: String,
    val venueId: Int,
    val runningTime: String,
    val minimumAge: String,
    val importantNotice: String,
    val mainImageUrl: String,
    val smallImageUrl: String,
    val specialGraphics: Int,
    val shortOfferText: String?,
    val LongOfferText: String?,
    val currentPrice: Float,
    val offerPrice: Float,
    val startDate: String,
    val endDate: String,
    val eventMinimumPrice: Float
)