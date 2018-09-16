package com.ch4k4uw.omdbapp

import com.ch4k4uw.crosscutting.ioc.design.AppDesignComponent
import com.ch4k4uw.omdbapp.ioc.DaggerAppComponentTest
import com.ch4k4uw.omdbapp.ioc.DaggerAppDesignComponentTest
import com.facebook.soloader.SoLoader
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class AppTest: App() {
    override val designComponent: AppDesignComponent
        get() = DaggerAppDesignComponentTest.builder().context(this).build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponentTest
            .builder()
            .designComponent(designComponent)
            .create(this)

    override fun onCreate() {
        SoLoader.setInTestMode()
        super.onCreate()
    }
}