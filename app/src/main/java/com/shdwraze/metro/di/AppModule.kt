package com.shdwraze.metro.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.shdwraze.metro.common.Constants.SERVER_URL
import com.shdwraze.metro.data.api.MetroApiService
import com.shdwraze.metro.data.api.result.ApiSafeCaller
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(SERVER_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideMetroApiService(retrofit: Retrofit): MetroApiService {
        return retrofit.create(MetroApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiSafeCaller(): ApiSafeCaller = ApiSafeCaller
}