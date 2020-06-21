package com.github.tommykw.musical.di

import android.app.Application
import com.github.tommykw.musical.data.local.MusicalDatabase
import com.github.tommykw.musical.data.network.MusicalRemoteDataSource
import com.github.tommykw.musical.data.network.MusicalService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, NetworkModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideEpisodeService(okhttpClient: OkHttpClient,
                              converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, MusicalService::class.java)


    @Singleton
    @Provides
    fun provideEpisodeRemoteDataSource(episodeService: MusicalService)
            = MusicalRemoteDataSource(episodeService)

    @Singleton
    @Provides
    fun provideCoroutineDispatcher() = Dispatchers.Default

    @Singleton
    @Provides
    fun provideDb(app: Application) = MusicalDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideEpisodeDao(db: MusicalDatabase) = db.musicalDao()

    @CoroutineScopeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

    private fun <T> provideService(okhttpClient: OkHttpClient,
                                   converterFactory: GsonConverterFactory, clazz: Class<T>): T {
        return createRetrofit(okhttpClient, converterFactory).create(clazz)
    }

    private fun createRetrofit(
            okhttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(MusicalService.BASE_URL)
                .client(okhttpClient)
                .addConverterFactory(converterFactory)
                .build()
    }
}