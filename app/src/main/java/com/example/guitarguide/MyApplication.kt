package com.example.guitarguide

import android.app.Application
import com.example.guitarguide.db.AppContainer
import com.example.guitarguide.db.AppDataContainer

class MyApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}