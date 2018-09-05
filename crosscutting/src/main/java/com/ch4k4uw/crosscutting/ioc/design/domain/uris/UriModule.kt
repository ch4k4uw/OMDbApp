package com.ch4k4uw.crosscutting.ioc.design.domain.uris

import com.ch4k4uw.crosscutting.scope.DesignScoped
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class UriModule {
    @Module
    companion object {
        @JvmStatic
        @Named("restUrl")
        @DesignScoped
        @Provides
        fun provideRestUrl()
                = "http://www.omdbapi.com"

        @JvmStatic
        @Named("apiKey")
        @DesignScoped
        @Provides
        fun provideApiKey()
                = "d7b91e80"
    }
}