package com.ch4k4uw.crosscutting.ioc.design.application

import com.ch4k4uw.application.dto.result.Movie
import com.ch4k4uw.application.dto.result.MovieDetail
import com.ch4k4uw.application.dto.result.MovieType
import com.ch4k4uw.application.service.SimpleDetailService
import com.ch4k4uw.application.service.SimpleListTypesService
import com.ch4k4uw.application.service.SimpleSearchService
import com.ch4k4uw.crosscutting.scope.DesignScoped
import com.ch4k4uw.domain.abstraction.scheduler.SchedulerProvider
import com.ch4k4uw.domain.common.abstraction.application.DetailApplicationService
import com.ch4k4uw.domain.common.abstraction.application.SearchApplicationService
import com.ch4k4uw.domain.moviecatalog.abstraction.application.ListTypesApplicationService
import com.ch4k4uw.infrastructure.scheduler.SimpleSchedulerProvider
import dagger.Binds
import dagger.Module

@Module
interface ApplicationModule {
    @DesignScoped
    @Binds
    fun bindDetailService(detailService: SimpleDetailService): DetailApplicationService<MovieDetail>

    @DesignScoped
    @Binds
    fun bindListTypesService(listTypesService: SimpleListTypesService): ListTypesApplicationService<MovieType>

    @DesignScoped
    @Binds
    fun bindSearchService(searchService: SimpleSearchService): SearchApplicationService<Movie>

    @DesignScoped
    @Binds
    fun bindSchedulerProvider(schedulerProvider: SimpleSchedulerProvider.SimpleIOSchedulerProvider): SchedulerProvider

}