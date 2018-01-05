package com.github.tommykw.musical.constant

import java.util.*

/**
 * Created by tommy on 15/10/06.
 */
object Constants {
    const val NAV_CAMERA = "camera"
    const val NAV_GALLERY = "gallery"
    const val NAV_SLIDESHOW = "slideshow"
    const val NAV_MANAGE = "manage"
    const val NAV_SHARE = "share"
    const val NAV_SEND = "send"
    const val FIREBASE_SAMPLE_URL = "https://boiling-fire-7413.firebaseio.com/"

    fun getUuid() = UUID.randomUUID()
}