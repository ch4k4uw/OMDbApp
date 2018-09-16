package com.ch4k4uw.omdbapp.ioc.module

import com.ch4k4uw.crosscutting.scope.DesignScoped
import com.ch4k4uw.domain.abstraction.scheduler.SchedulerProvider
import com.ch4k4uw.infrastructure.scheduler.SimpleSchedulerProvider
import dagger.Binds
import dagger.Module

@Module
interface SchedulerProviderModuleTest {
    @DesignScoped
    @Binds
    fun bindSchedulerProvider(provider: SimpleSchedulerProvider.SimpleAndroidSchedulerProvider): SchedulerProvider
}