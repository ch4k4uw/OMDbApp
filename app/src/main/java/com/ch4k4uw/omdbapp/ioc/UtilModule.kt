package com.ch4k4uw.omdbapp.ioc

import com.ch4k4uw.crosscutting.scope.PresentationScoped
import com.ch4k4uw.omdbapp.fragmentutil.SimpleAppFragmentManager
import com.ch4k4uw.omdbapp.fragmentutil.abstraction.AppFragmentManager
import dagger.Binds
import dagger.Module

@Module
interface UtilModule {
    @PresentationScoped
    @Binds
    fun bindAppFragmentManager(manager: SimpleAppFragmentManager): AppFragmentManager
}