package tokyo.tommy_kw.musical.constant

import java.util.*

/**
 * Created by tommy on 15/10/06.
 */
class Constants {
    companion object {
        val MAIN_ACTIVITY_NAME = "main_activity"
        val SECOND_ACTIVITY_NAME = "second_activity"
        val CONTENT_FRAGMENT_NAME = "content_fragment_name"
        val NAV_CAMERA = "camera"
        val NAV_GALLERY = "gallery"
        val NAV_SLIDESHOW = "slideshow"
        val NAV_MANAGE = "manage"
        val NAV_SHARE = "share"
        val NAV_SEND = "send"
        fun getUuid() = UUID.randomUUID()

        val FIREBASE_SAMPLE_URL = "https://boiling-fire-7413.firebaseio.com/"
    }
}