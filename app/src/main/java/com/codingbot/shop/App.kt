package com.codingbot.shop


import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.codingbot.shop.core.common.AppLifecycleObserver
import com.codingbot.shop.core.common.Logger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    private val logger = Logger("AppLogger")

    private val appLifecycleObserver = AppLifecycleObserver()

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(appLifecycleObserver)
    }
}