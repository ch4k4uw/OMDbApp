package com.ch4k4uw.crosscutting.ioc

import com.ch4k4uw.application.dto.result.Movie
import com.ch4k4uw.application.dto.result.MovieDetail
import com.ch4k4uw.application.dto.result.MovieType
import com.ch4k4uw.crosscutting.App
import com.ch4k4uw.crosscutting.ioc.design.AppDesignComponent
import com.ch4k4uw.crosscutting.scope.PresentationScoped
import com.ch4k4uw.domain.common.abstraction.application.DetailApplicationService
import com.ch4k4uw.domain.common.abstraction.application.SearchApplicationService
import com.ch4k4uw.domain.moviecatalog.abstraction.application.ListTypesApplicationService
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PresentationScoped
@Component(modules = [AppModule::class, AndroidInjectionModule::class, AndroidSupportInjectionModule::class], dependencies = [AppDesignComponent::class])
abstract class AppComponent: AndroidInjector<App> {
    abstract val detailService: DetailApplicationService<MovieDetail>

    abstract val listTypesService: ListTypesApplicationService<MovieType>

    abstract val searchService: SearchApplicationService<Movie>

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<App>() {
        abstract fun designComponent(component: AppDesignComponent): AppComponent.Builder
        abstract override fun build(): AppComponent
    }
}