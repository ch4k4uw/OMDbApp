package com.ch4k4uw.omdbapp.mvp.detailfragment

import com.ch4k4uw.crosscutting.scope.FragmentScoped
import com.ch4k4uw.omdbapp.mvp.detailfragment.abstraction.DetailPresenter
import dagger.Binds
import dagger.Module

@Module
interface DetailModule {
    @FragmentScoped
    @Binds
    fun bindPresenter(presenter: SimpleDetailPresenter): DetailPresenter

}