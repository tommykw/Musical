package com.github.tommykw.musical.test.entity

import android.support.test.runner.AndroidJUnit4
import com.google.gson.Gson
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import com.github.tommykw.musical.data.entity.Event

@RunWith(AndroidJUnit4::class)
class WeatherTest {
    private var jsonString = "{\"coord\":{\"lon\":139.69,\"lat\":35.69},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"Sky is Clear\",\"icon\":\"01n\"}],\"base\":\"stations\",\"main\":{\"temp\":290.05,\"pressure\":1005,\"humidity\":42,\"temp_min\":286.15,\"temp_max\":295.37},\"visibility\":10000,\"wind\":{\"speed\":4.1,\"deg\":280},\"clouds\":{\"all\":0},\"dt\":1444343823,\"sys\":{\"type\":1,\"id\":7619,\"message\":0.0171,\"country\":\"JP\",\"sunrise\":1444250515,\"sunset\":1444292098},\"id\":1850147,\"name\":\"Tokyo\",\"cod\":200}"

    // Property must be initialized...
    // @Mock
    // var gson: Gson

    @Before
    fun setUp() {
        var json = Gson().fromJson(jsonString, Event::class.java)
        MockitoAnnotations.initMocks(this)
        //Mockito.`when`(json.coord.lon).thenReturn(139.69)
        //Mockito.`when`(json.coord.lat).thenReturn(35.69)
    }

    @Test
    fun testJson() {
        Assert.assertThat(1, CoreMatchers.`is`(1))
    }
}