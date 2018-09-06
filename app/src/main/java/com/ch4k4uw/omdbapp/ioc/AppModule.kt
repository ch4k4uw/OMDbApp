package com.ch4k4uw.omdbapp.ioc

import com.ch4k4uw.crosscutting.scope.FragmentScoped
import com.ch4k4uw.omdbapp.MainFragment
import com.ch4k4uw.omdbapp.mvp.mainfragment.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AppModule {
    @FragmentScoped
    @ContributesAndroidInjector(modules = [MainModule::class])
    fun bindMainFragment(): MainFragment

}