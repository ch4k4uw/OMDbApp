package com.ch4k4uw.omdbapp.ioc

import com.ch4k4uw.crosscutting.scope.PresentationScoped
import com.ch4k4uw.omdbapp.rx.ComposerProvider
import com.ch4k4uw.omdbapp.rx.SimpleComposerProvider
import dagger.Binds
import dagger.Module

@Module
interface ComposerProviderModule {
    @PresentationScoped
    @Binds
    fun bindComposerProvider(provider: SimpleComposerProvider): ComposerProvider

}