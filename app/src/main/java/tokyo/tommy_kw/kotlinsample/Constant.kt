package tokyo.tommy_kw.kotlinsample

import java.util.*

/**
 * Created by tommy on 15/10/06.
 */
public class Constant {
    companion object {
        val MAIN_ACTIVITY_NAME = "main_activity"
        val SECOND_ACTIVITY_NAME = "second_activity"
        val CONTENT_FRAGMENT_NAME = "content_fragment_name"
        fun getUuid() = UUID.randomUUID()
    }
}