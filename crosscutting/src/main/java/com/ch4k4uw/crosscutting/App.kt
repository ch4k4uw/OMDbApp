package com.ch4k4uw.crosscutting

import com.ch4k4uw.crosscutting.ioc.AppComponent
import com.ch4k4uw.crosscutting.ioc.DaggerAppComponent
import com.ch4k4uw.crosscutting.ioc.design.AppDesignComponent
import com.ch4k4uw.crosscutting.ioc.design.DaggerAppDesignComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App: DaggerApplication() {
    private lateinit var mAppComponent: AppComponent
    private lateinit var mDesignComponent: AppDesignComponent
    var designComponent: AppDesignComponent
        get() {
            if(::mDesignComponent.isInitialized) {
                return mDesignComponent
            }

            mDesignComponent = DaggerAppDesignComponent
                    .builder()
                    .context(this)
                    .build()

            return mDesignComponent
        }
        set(value) {
            mDesignComponent = value
        }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        if(::mAppComponent.isInitialized) {
            return mAppComponent
        }
        mAppComponent = DaggerAppComponent.builder().designComponent(designComponent).create(this) as AppComponent

        return mAppComponent

    }

    override fun onCreate() {
        super.onCreate()

        val svc1 = mAppComponent.detailService
        val svc2 = mAppComponent.detailService
        val svc3 = mAppComponent.detailService

    }

}