package com.ch4k4uw.crosscutting.ioc.design.domain.common.factory

import com.ch4k4uw.crosscutting.scope.DesignScoped
import com.ch4k4uw.domain.common.abstraction.factory.entity.CommonEntityFactory
import com.ch4k4uw.domain.common.abstraction.factory.specification.MovieQueryRepositorySpecificationFactory
import com.ch4k4uw.domain.common.abstraction.factory.value.CommonValueFactory
import com.ch4k4uw.domain.common.factory.entity.SimpleCommonEntityFactory
import com.ch4k4uw.domain.common.factory.value.SimpleCommonValueFactory
import com.ch4k4uw.infrastructure.factory.specification.SimpleMovieQueryRepositorySpecificationFactory
import dagger.Binds
import dagger.Module

@Module
interface CommonFactoryModule {
    @DesignScoped
    @Binds
    fun bindEntityFactory(entityFactory: SimpleCommonEntityFactory): CommonEntityFactory

    @DesignScoped
    @Binds
    fun bindValueFactory(valueFactory: SimpleCommonValueFactory): CommonValueFactory

    @DesignScoped
    @Binds
    fun bindRepositorySpecFactory(specFactory: SimpleMovieQueryRepositorySpecificationFactory): MovieQueryRepositorySpecificationFactory

}