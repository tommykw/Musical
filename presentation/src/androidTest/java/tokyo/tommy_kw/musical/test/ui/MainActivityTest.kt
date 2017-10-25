package com.github.tommykw.musical.test.ui

import android.test.ActivityInstrumentationTestCase2
import com.robotium.solo.Solo
import com.squareup.spoon.Spoon
import com.github.tommykw.musical.activity.MainActivity
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.github.tommykw.musical.R

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

    fun testInitialViews() {
        onView(withId(R.id.name)).check(matches(withText("name")))
        onView(withId(R.id.base)).check(matches(withText("base")))
        onView(withId(R.id.lat)).check(matches(withText("lat")))
        onView(withId(R.id.lon)).check(matches(withText("lon")))
    }
}