package com.ch4k4uw.omdbapp.ioc

import com.ch4k4uw.crosscutting.ioc.design.AppDesignComponent
import com.ch4k4uw.crosscutting.ioc.design.application.ApplicationModule
import com.ch4k4uw.crosscutting.ioc.design.domain.common.factory.CommonFactoryModule
import com.ch4k4uw.crosscutting.ioc.design.domain.common.repository.CommonRepositoryModule
import com.ch4k4uw.crosscutting.ioc.design.domain.moviecatalog.factory.MovieCatalogFactoryModule
import com.ch4k4uw.crosscutting.ioc.design.domain.moviecatalog.repository.MovieCatalogRepositoryModule
import com.ch4k4uw.crosscutting.ioc.design.domain.uris.UriModule
import com.ch4k4uw.crosscutting.scope.DesignScoped
import com.ch4k4uw.omdbapp.ioc.module.InfrastructureModuleTest
import com.ch4k4uw.omdbapp.ioc.module.SchedulerProviderModuleTest
import dagger.Component

@DesignScoped
@Component(modules = [
    SchedulerProviderModuleTest::class,
    ApplicationModule::class,
    CommonRepositoryModule::class,
    CommonFactoryModule::class,
    MovieCatalogFactoryModule::class,
    MovieCatalogRepositoryModule::class,
    UriModule::class,
    InfrastructureModuleTest::class
])
interface AppDesignComponentTest: AppDesignComponent {
    @Component.Builder
    interface Builder: AppDesignComponent.Builder

}