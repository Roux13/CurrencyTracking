package ru.nehodov.currencytracking.di.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.nehodov.currencytracking.data.network.ApiService
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideApiService(
        gsonFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(gsonFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit,
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}