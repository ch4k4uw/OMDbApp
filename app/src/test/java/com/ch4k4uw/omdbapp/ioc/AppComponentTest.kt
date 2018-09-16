package com.ch4k4uw.omdbapp.ioc

import com.ch4k4uw.crosscutting.ioc.design.AppDesignComponent
import com.ch4k4uw.crosscutting.scope.PresentationScoped
import com.ch4k4uw.omdbapp.App
import com.ch4k4uw.omdbapp.ioc.module.SimpleConstantsModuleTest
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PresentationScoped
@Component(modules = [AppModule::class, SimpleConstantsModuleTest::class, AndroidInjectionModule::class, AndroidSupportInjectionModule::class], dependencies = [AppDesignComponent::class])
abstract class AppComponentTest: AppComponent() {
    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<App>() {
        abstract fun designComponent(component: AppDesignComponent): Builder

        abstract override fun build(): AppComponent

    }
}