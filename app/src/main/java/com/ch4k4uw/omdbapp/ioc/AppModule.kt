package com.ch4k4uw.omdbapp.ioc

import com.ch4k4uw.crosscutting.scope.FragmentScoped
import com.ch4k4uw.omdbapp.MainFragment
import com.ch4k4uw.omdbapp.mvp.detailfragment.DetailFragment
import com.ch4k4uw.omdbapp.mvp.detailfragment.DetailModule
import com.ch4k4uw.omdbapp.mvp.mainfragment.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ComposerProviderModule::class, UtilModule::class])
interface AppModule {
    @FragmentScoped
    @ContributesAndroidInjector(modules = [MainModule::class])
    fun bindMainFragment(): MainFragment

    @FragmentScoped
    @ContributesAndroidInjector(modules = [DetailModule::class])
    fun bindDetailFragment(): DetailFragment

}