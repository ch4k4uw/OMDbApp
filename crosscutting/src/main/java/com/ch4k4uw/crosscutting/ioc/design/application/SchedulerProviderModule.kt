package com.ch4k4uw.crosscutting.ioc.design.application

import com.ch4k4uw.crosscutting.scope.DesignScoped
import com.ch4k4uw.domain.abstraction.scheduler.SchedulerProvider
import com.ch4k4uw.infrastructure.scheduler.SimpleSchedulerProvider
import dagger.Binds
import dagger.Module

@Module
interface SchedulerProviderModule {
    @DesignScoped
    @Binds
    fun bindSchedulerProvider(scheduler: SimpleSchedulerProvider.SimpleIOSchedulerProvider): SchedulerProvider

}