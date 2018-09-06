package com.ch4k4uw.omdbapp.mvp.mainfragment

import com.ch4k4uw.crosscutting.scope.FragmentScoped
import com.ch4k4uw.omdbapp.mvp.mainfragment.abstraction.MainPresenter
import dagger.Binds
import dagger.Module

@Module
interface MainModule {
    @FragmentScoped
    @Binds
    fun bindPresenter(presenter: SimpleMainPresenter): MainPresenter
}