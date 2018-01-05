package com.github.tommykw.data

data class Venue(
    val venueId: Int,
    val name: String,
    val info: String,
    val address: String,
    val city: String,
    val postcode: String?,
    val telephone: String?,
    val fax: String?,
    val email: String?,
    val nearestTube: String?,
    val train: String?,
    val planLink: String,
    val imageUrl: String
)
