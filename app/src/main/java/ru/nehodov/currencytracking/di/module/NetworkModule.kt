package ru.nehodov.currencytracking.di.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.nehodov.currencytracking.data.network.ApiConstants
import ru.nehodov.currencytracking.data.network.ApiService
import ru.nehodov.currencytracking.data.network.HeaderInterceptor
import ru.nehodov.currencytracking.data.providers.BuildInfoProvider

@Module
object NetworkModule {

    @Provides
    fun provideGson(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideHeaderInterceptor(buildInfoProvider: BuildInfoProvider): HeaderInterceptor {
        return HeaderInterceptor(buildInfoProvider)
    }

    @Provides
    fun provideOkhttp(headerInterceptor: HeaderInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .build()
    }

    @AppScope
    @Provides
    fun provideRetrofit(
        gsonFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonFactory)
            .build()
    }

    @AppScope
    @Provides
    fun provideApiService(
        retrofit: Retrofit,
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}