package tokyo.tommy_kw.musical.api

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
import tokyo.tommy_kw.musical.entity.Weather
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by tommy on 15/10/08.
 */
class ApiClient {
    companion object {
        private val ENDPOINT = "http://api.openweathermap.org/data/"
        private val CONNECT_TIMEOUT_MILLIS: Long = 10 * 1000
        private val READ_TIMEOUT_MILLIS: Long = 20 * 1000
        private var mApiService: ApiService

        init {
            mApiService = getRestAdapter().create(ApiService::class.java)
        }

        fun getApiClient(): ApiService {
            if (mApiService != null) {
                return mApiService
            }

            mApiService = getRestAdapter().create(ApiService::class.java)
            return mApiService
        }

        fun getOkClient(): OkClient {
            val client = OkHttpClient()
            client.setConnectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            client.setReadTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            client.networkInterceptors().add(StethoInterceptor())
            return OkClient(client)
        }

        fun getWeather(): Observable<Weather> {
            return mApiService.getWeather()
        }

        private fun getRestAdapter(): RestAdapter {
            val gsonBuilder = GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .registerTypeAdapter(Date::class.java, DateTypeAdapter()).create()

            return RestAdapter.Builder()
                    .setEndpoint(ENDPOINT)
                    .setConverter(GsonConverter(gsonBuilder))
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setLog(AndroidLog("=NETWORK="))
                    .setClient(getOkClient())
                    .build();
        }
    }
}