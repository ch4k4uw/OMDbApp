package com.ch4k4uw.crosscutting.ioc.design

import com.ch4k4uw.crosscutting.ioc.design.application.ApplicationModule
import com.ch4k4uw.crosscutting.ioc.design.domain.common.factory.CommonFactoryModule
import com.ch4k4uw.crosscutting.ioc.design.domain.common.repository.CommonRepositoryModule
import com.ch4k4uw.crosscutting.ioc.design.domain.moviecatalog.factory.MovieCatalogFactoryModule
import com.ch4k4uw.crosscutting.ioc.design.domain.moviecatalog.repository.MovieCatalogRepositoryModule
import com.ch4k4uw.crosscutting.ioc.design.domain.uris.UriModule
import com.ch4k4uw.crosscutting.ioc.design.infrastructure.InfrastructureModule
import dagger.Module

@Module(includes = [
    ApplicationModule::class,
    CommonRepositoryModule::class,
    CommonFactoryModule::class,
    MovieCatalogFactoryModule::class,
    MovieCatalogRepositoryModule::class,
    UriModule::class,
    InfrastructureModule::class
])
interface AppDesignModule