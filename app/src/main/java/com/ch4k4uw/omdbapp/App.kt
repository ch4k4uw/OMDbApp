package com.ch4k4uw.omdbapp

import com.ch4k4uw.crosscutting.ioc.design.AppDesignComponent
import com.ch4k4uw.crosscutting.ioc.design.DaggerAppDesignComponent
import com.ch4k4uw.omdbapp.ioc.DaggerAppComponent
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

open class App: DaggerApplication() {
    protected open val designComponent: AppDesignComponent
        get() = DaggerAppDesignComponent
                .builder()
                .context(this)
                .build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent
            .builder()
            .designComponent(designComponent)
            .create(this)


    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}