package com.shdwraze.metro.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.shdwraze.metro.data.api.MetroApiService
import com.shdwraze.metro.data.repository.NetworkStationRepository
import com.shdwraze.metro.data.repository.StationRepository
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
            .baseUrl("https://backend-service-bizc.onrender.com")
            .build()
    }

    @Provides
    @Singleton
    fun provideMetroApiService(retrofit: Retrofit): MetroApiService {
        return retrofit.create(MetroApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideStationRepository(metroApiService: MetroApiService): StationRepository {
        return NetworkStationRepository(metroApiService)
    }

}