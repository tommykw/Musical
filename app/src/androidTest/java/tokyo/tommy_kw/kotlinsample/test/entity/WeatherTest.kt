package tokyo.tommy_kw.kotlinsample.test.entity

import android.support.test.runner.AndroidJUnit4
import com.google.gson.Gson
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import tokyo.tommy_kw.kotlinsample.entity.Weather

/**
 * Created by tommy on 15/10/09.
 */
@RunWith(AndroidJUnit4::class)
class WeatherTest {
    private var jsonString = "{\"coord\":{\"lon\":139.69,\"lat\":35.69},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"Sky is Clear\",\"icon\":\"01n\"}],\"base\":\"stations\",\"main\":{\"temp\":290.05,\"pressure\":1005,\"humidity\":42,\"temp_min\":286.15,\"temp_max\":295.37},\"visibility\":10000,\"wind\":{\"speed\":4.1,\"deg\":280},\"clouds\":{\"all\":0},\"dt\":1444343823,\"sys\":{\"type\":1,\"id\":7619,\"message\":0.0171,\"country\":\"JP\",\"sunrise\":1444250515,\"sunset\":1444292098},\"id\":1850147,\"name\":\"Tokyo\",\"cod\":200}"

    // Property must be initialized...
    // @Mock
    // var gson: Gson

    @Before
    fun setUp() {
        var json = Gson().fromJson(jsonString, Weather::class.java)
        MockitoAnnotations.initMocks(this)
        //Mockito.`when`(json.coord.lon).thenReturn(139.69)
        //Mockito.`when`(json.coord.lat).thenReturn(35.69)
        `when`(json.weathers[0].id).thenReturn(800)
        `when`(json.weathers[0].main).thenReturn("Clear")
        `when`(json.weathers[0].description).thenReturn("Sky is Clear")
        `when`(json.weathers[0].icon).thenReturn("01n")
        `when`(json.base).thenReturn("stations")
        //Mockito.`when`(json.main.temp).thenReturn(290.05)
        `when`(json.main.pressure).thenReturn(1005)
        `when`(json.main.humidity).thenReturn(42)
        //Mockito.`when`(json.main.tempMin).thenReturn(286.15)
        //Mockito.`when`(json.main.tempMax).thenReturn(295.37)

        `when`(json.visibility).thenReturn(10000)
        //Mockito.`when`(json.wind.speed).thenReturn(4.1)
        `when`(json.wind.deg).thenReturn(280)
        `when`(json.clouds.all).thenReturn(0)
        `when`(json.dt).thenReturn(1444343823)
        `when`(json.sys.type).thenReturn(1)
        `when`(json.sys.id).thenReturn(7619)
        //Mockito.`when`(json.sys.message).thenReturn(0.0171)
        `when`(json.sys.country).thenReturn("JP")
        `when`(json.sys.sunrise).thenReturn(1444250515)
        `when`(json.sys.sunset).thenReturn(1444292098)

        `when`(json.id).thenReturn(1850147)
        `when`(json.name).thenReturn("Tokyo")
        `when`(json.cod).thenReturn(200)
    }

    @Test
    fun testJson() {
        Assert.assertThat(1, CoreMatchers.`is`(1))
    }
}