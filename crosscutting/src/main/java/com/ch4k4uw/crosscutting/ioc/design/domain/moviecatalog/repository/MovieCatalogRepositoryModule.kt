package com.ch4k4uw.crosscutting.ioc.design.domain.moviecatalog.repository

import com.ch4k4uw.crosscutting.scope.DesignScoped
import com.ch4k4uw.domain.moviecatalog.abstraction.repository.MovieTypeQueryRepository
import com.ch4k4uw.infrastructure.repository.SimpleMovieTypeQueryRepository
import dagger.Binds
import dagger.Module

@Module
interface MovieCatalogRepositoryModule {
    @DesignScoped
    @Binds
    fun bindMovieTypeQueryRepository(repository: SimpleMovieTypeQueryRepository): MovieTypeQueryRepository

}