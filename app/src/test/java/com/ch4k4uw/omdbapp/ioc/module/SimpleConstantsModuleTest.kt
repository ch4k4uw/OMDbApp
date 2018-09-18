package com.ch4k4uw.omdbapp.ioc.module

import com.ch4k4uw.crosscutting.scope.PresentationScoped
import com.ch4k4uw.omdbapp.ioc.abstraction.ConstantsModule
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SimpleConstantsModuleTest: ConstantsModule {
    @PresentationScoped
    @Provides
    @Named("app_animations")
    override fun provideAppAnimationsConst(): Boolean
            = false

    @PresentationScoped
    @Provides
    @Named("app_search_debounce")
    override fun provideSearchDebounceConst(): Boolean
            = false
}