package com.shdwraze.metro.di

import com.shdwraze.metro.data.repository.CommonRepository
import com.shdwraze.metro.data.repository.NetworkCommonRepository
import com.shdwraze.metro.data.repository.NetworkStationRepository
import com.shdwraze.metro.data.repository.StationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindStationRepository(
        stationRepository: NetworkStationRepository
    ): StationRepository

    @Binds
    @Singleton
    abstract fun bindCommonRepository(
        commonRepository: NetworkCommonRepository
    ): CommonRepository
}