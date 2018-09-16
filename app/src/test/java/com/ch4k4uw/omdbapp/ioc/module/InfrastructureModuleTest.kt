package com.ch4k4uw.omdbapp.ioc.module

import com.ch4k4uw.crosscutting.scope.DesignScoped
import com.ch4k4uw.infrastructure.abstraction.rest.controller.RestController
import com.ch4k4uw.infrastructure.rest.controller.FakeRestController
import dagger.Binds
import dagger.Module

@Module
interface InfrastructureModuleTest {
    @DesignScoped
    @Binds
    fun bindRestController(restController: FakeRestController): RestController
}