package tokyo.tommy_kw.kotlinsample.api

import retrofit.http.GET
import rx.Observable
import tokyo.tommy_kw.kotlinsample.entity.Weather

/**
 * Created by tommy on 15/10/07.
 */
interface ApiService {
    companion object {
        val VERSION = "2.5"
    }
    @GET("/${VERSION}/weather?q=Tokyo,jp")
    fun getWeather() : Observable<Array<Weather>>
}