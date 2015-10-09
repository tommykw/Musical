package tokyo.tommy_kw.kotlinsample.entity

import org.hamcrest.CoreMatchers
import org.json.JSONObject
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by tommy on 15/10/09.
 */
class WeatherTest {
    internal var json = "{\"coord\":{\"lon\":139.69,\"lat\":35.69},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"Sky is Clear\",\"icon\":\"01n\"}],\"base\":\"stations\",\"main\":{\"temp\":290.05,\"pressure\":1005,\"humidity\":42,\"temp_min\":286.15,\"temp_max\":295.37},\"visibility\":10000,\"wind\":{\"speed\":4.1,\"deg\":280},\"clouds\":{\"all\":0},\"dt\":1444343823,\"sys\":{\"type\":1,\"id\":7619,\"message\":0.0171,\"country\":\"JP\",\"sunrise\":1444250515,\"sunset\":1444292098},\"id\":1850147,\"name\":\"Tokyo\",\"cod\":200}"

    // Property must be initialized
    // @Mock
    // var jsonObject: JSONObject

    @Before
    @Throws(Exception::class)
    fun setUp() {
        val jsonObject = JSONObject(json)
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(jsonObject.getString("id")).thenReturn("1850147")
        Mockito.`when`(jsonObject.getString("name")).thenReturn("Tokyo")
    }

    @Test
    fun testJson() {
        Assert.assertThat(1, CoreMatchers.`is`(1))
    }
}