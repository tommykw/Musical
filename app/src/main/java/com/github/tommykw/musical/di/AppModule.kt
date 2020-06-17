package com.github.tommykw.musical.di

import android.app.Application
import com.github.tommykw.musical.data.local.EpisodeDatabase
import com.github.tommykw.musical.data.network.EpisodeRemoteDataSource
import com.github.tommykw.musical.data.network.EpisodeService
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
    ) = provideService(okhttpClient, converterFactory, EpisodeService::class.java)


    @Singleton
    @Provides
    fun provideEpisodeRemoteDataSource(episodeService: EpisodeService)
            = EpisodeRemoteDataSource(episodeService)

    @Singleton
    @Provides
    fun provideCoroutineDispatcher() = Dispatchers.Default

    @Singleton
    @Provides
    fun provideDb(app: Application) = EpisodeDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideEpisodeDao(db: EpisodeDatabase) = db.episodeDao()

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
                .baseUrl(EpisodeService.BASE_URL)
                .client(okhttpClient)
                .addConverterFactory(converterFactory)
                .build()
    }
}