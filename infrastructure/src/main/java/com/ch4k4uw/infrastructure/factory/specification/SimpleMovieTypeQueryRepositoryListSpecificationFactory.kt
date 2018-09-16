package com.ch4k4uw.infrastructure.factory.specification

import com.ch4k4uw.domain.moviecatalog.abstraction.factory.specification.MovieTypeQueryRepositoryListSpecificationFactory
import com.ch4k4uw.domain.moviecatalog.abstraction.repository.specification.MovieTypeQueryRepositoryListSpecitication
import com.ch4k4uw.infrastructure.repository.specification.SimpleMovieTypeQueryRepositoryListSpecitication
import javax.inject.Inject

class SimpleMovieTypeQueryRepositoryListSpecificationFactory @Inject constructor(): MovieTypeQueryRepositoryListSpecificationFactory {
    override fun newListSpec(): MovieTypeQueryRepositoryListSpecitication
            = SimpleMovieTypeQueryRepositoryListSpecitication()
}