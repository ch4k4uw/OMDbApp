package com.ch4k4uw.crosscutting.ioc.design

import android.content.Context
import com.ch4k4uw.application.dto.result.Movie
import com.ch4k4uw.application.dto.result.MovieDetail
import com.ch4k4uw.application.dto.result.MovieType
import com.ch4k4uw.crosscutting.scope.DesignScoped
import com.ch4k4uw.domain.common.abstraction.application.DetailApplicationService
import com.ch4k4uw.domain.common.abstraction.application.SearchApplicationService
import com.ch4k4uw.domain.moviecatalog.abstraction.application.ListTypesApplicationService
import dagger.BindsInstance
import dagger.Component

@DesignScoped
@Component(modules = [AppDesignModule::class])
interface AppDesignComponent {
    val detailService: DetailApplicationService<MovieDetail>

    val listTypesService: ListTypesApplicationService<MovieType>

    val searchService: SearchApplicationService<Movie>

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppDesignComponent

    }
}