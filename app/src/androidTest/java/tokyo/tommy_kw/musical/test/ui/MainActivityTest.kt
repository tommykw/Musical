package tokyo.tommy_kw.musical.test.ui

import android.test.ActivityInstrumentationTestCase2
import com.robotium.solo.Solo
import com.squareup.spoon.Spoon
import tokyo.tommy_kw.musical.activity.MainActivity

/**
 * Created by tommy on 15/10/17.
 */
class MainActivityTest : ActivityInstrumentationTestCase2<MainActivity>(MainActivity::class.java) {
    private var solo: Solo? = null
    init {}

    override fun setUp() {
        solo = Solo(instrumentation, activity)
        super.setUp()
    }

    @Throws(Exception::class)
    override fun tearDown() {
        solo!!.finishOpenedActivities()
        super.tearDown()
    }

    fun testMainActivity() {
        Spoon.screenshot(activity, "start")
        Spoon.screenshot(activity, "end")
    }
}