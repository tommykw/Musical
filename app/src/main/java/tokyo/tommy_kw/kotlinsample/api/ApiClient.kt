package tokyo.tommy_kw.kotlinsample.api

import com.facebook.stetho.okhttp.StethoInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.DateTypeAdapter
import com.squareup.okhttp.OkHttpClient
import retrofit.RestAdapter
import retrofit.android.AndroidLog
import retrofit.client.OkClient
import retrofit.converter.GsonConverter
import rx.Observable
import rx.functions.Func1
import tokyo.tommy_kw.kotlinsample.entity.Weather
import java.util.*

/**
 * Created by tommy on 15/10/08.
 */
class ApiClient {
    private val ENDPOINT = "http://api.openweathermap.org/data/"
    private val mRestAdapter:RestAdapter
    private val mApiService:ApiService

    init {
        val gsonBuilder = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date::class.java, DateTypeAdapter()).create()

        val client = OkHttpClient()
        client.networkInterceptors().add(StethoInterceptor())

        mRestAdapter = RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setConverter(GsonConverter(gsonBuilder))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(AndroidLog("=NETWORK="))
                .setClient(OkClient(client))
                .build();

        mApiService = mRestAdapter.create(ApiService::class.java)
    }

    fun getWeather(): Observable<Weather> {
        return mApiService.getWeather();
    }
}