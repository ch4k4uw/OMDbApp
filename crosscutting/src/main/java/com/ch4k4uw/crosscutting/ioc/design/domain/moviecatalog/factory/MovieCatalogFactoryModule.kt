package com.ch4k4uw.crosscutting.ioc.design.domain.moviecatalog.factory

import com.ch4k4uw.crosscutting.scope.DesignScoped
import com.ch4k4uw.domain.moviecatalog.abstraction.factory.command.MovieCatalogCommandFactory
import com.ch4k4uw.domain.moviecatalog.abstraction.factory.command.MovieDetailCommandFactory
import com.ch4k4uw.domain.moviecatalog.abstraction.factory.command.MovieTypeCommandFactory
import com.ch4k4uw.domain.moviecatalog.abstraction.factory.specification.MovieTypeQueryRepositoryListSpecificationFactory
import com.ch4k4uw.domain.moviecatalog.factory.command.SimpleMovieCatalogCommandFactory
import com.ch4k4uw.domain.moviecatalog.factory.command.SimpleMovieDetailCommandFactory
import com.ch4k4uw.domain.moviecatalog.factory.command.SimpleMovieTypeCommandFactory
import com.ch4k4uw.infrastructure.factory.specification.SimpleMovieTypeQueryRepositoryListSpecificationFactory
import dagger.Binds
import dagger.Module

@Module
interface MovieCatalogFactoryModule {
    @DesignScoped
    @Binds
    fun bindMovieCatalogCommandFactory(commandFactory: SimpleMovieCatalogCommandFactory): MovieCatalogCommandFactory

    @DesignScoped
    @Binds
    fun bindMovieDetailCommandFactory(commandFactory: SimpleMovieDetailCommandFactory): MovieDetailCommandFactory

    @DesignScoped
    @Binds
    fun bindMovieTypeCommandFactory(commandFactory: SimpleMovieTypeCommandFactory): MovieTypeCommandFactory

    @DesignScoped
    @Binds
    fun bindMovieTypeQueryRepositoryListSpecFactory(specFactory: SimpleMovieTypeQueryRepositoryListSpecificationFactory): MovieTypeQueryRepositoryListSpecificationFactory

}