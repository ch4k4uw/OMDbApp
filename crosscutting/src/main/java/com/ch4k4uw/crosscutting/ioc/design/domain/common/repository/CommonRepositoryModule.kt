package com.ch4k4uw.crosscutting.ioc.design.domain.common.repository

import com.ch4k4uw.crosscutting.scope.DesignScoped
import com.ch4k4uw.domain.common.abstraction.repository.MovieQueryRepository
import com.ch4k4uw.infrastructure.repository.SimpleMovieQueryRepository
import dagger.Binds
import dagger.Module

@Module
interface CommonRepositoryModule {
    @DesignScoped
    @Binds
    fun bindMovieQueryRepository(repository: SimpleMovieQueryRepository): MovieQueryRepository

}