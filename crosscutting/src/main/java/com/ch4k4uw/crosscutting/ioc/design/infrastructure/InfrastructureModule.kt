package com.ch4k4uw.crosscutting.ioc.design.infrastructure

import com.ch4k4uw.crosscutting.scope.DesignScoped
import com.ch4k4uw.infrastructure.abstraction.rest.controller.RestController
import com.ch4k4uw.infrastructure.rest.controller.SimpleRestController
import dagger.Binds
import dagger.Module

@Module
interface InfrastructureModule {
    @DesignScoped
    @Binds
    fun bindRestController(restController: SimpleRestController): RestController

}