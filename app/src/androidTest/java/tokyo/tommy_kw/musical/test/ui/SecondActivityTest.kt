package com.github.tommykw.musical.test.ui

import android.test.ActivityInstrumentationTestCase2
import com.robotium.solo.Solo
import com.squareup.spoon.Spoon
import com.github.tommykw.musical.activity.SecondActivity

/**
 * Created by tommy on 15/10/18.
 */
class SecondActivityTest : ActivityInstrumentationTestCase2<SecondActivity>(SecondActivity::class.java) {
    private var mSolo: Solo? = null

    override fun setUp() {
        mSolo = Solo(instrumentation, activity)
        super.setUp()

    }

    @Throws(Exception::class)
    override fun tearDown() {
        mSolo!!.finishOpenedActivities()
        super.tearDown()
    }

    fun testSecondActivity() {
        Spoon.screenshot(activity, "start")
        Spoon.screenshot(activity, "end")
    }
}