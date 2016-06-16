package tokyo.tommy_kw.musical.api

import retrofit.http.GET
import rx.Observable
import tokyo.tommy_kw.musical.entity.Weather

/**
 * Created by tommy on 15/10/07.
 */
interface ApiService {
    companion object {
        val VERSION = "2.5"
    }

    @GET("/2.5/weather?q=Tokyo,jp")
    fun getWeather(): Observable<Weather>
}