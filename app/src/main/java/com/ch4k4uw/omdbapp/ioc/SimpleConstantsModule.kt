package com.ch4k4uw.omdbapp.ioc

import com.ch4k4uw.crosscutting.scope.PresentationScoped
import com.ch4k4uw.omdbapp.ioc.abstraction.ConstantsModule
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SimpleConstantsModule: ConstantsModule {
    @PresentationScoped
    @Provides
    @Named("app_animations")
    override fun provideAppAnimationsConst(): Boolean
            = true
}