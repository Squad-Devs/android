package com.shdwraze.metro

import android.app.Application
import com.shdwraze.metro.data.AppContainer
import com.shdwraze.metro.data.DefaultAppContainer

class MetroApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}