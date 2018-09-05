package com.ch4k4uw.infrastructure.factory.specification

import com.ch4k4uw.domain.moviecatalog.abstraction.factory.specification.MovieTypeQueryRepositoryListSpeciticationFactory
import com.ch4k4uw.domain.moviecatalog.abstraction.repository.specification.MovieTypeQueryRepositoryListSpecitication
import com.ch4k4uw.infrastructure.repository.specification.SimpleMovieTypeQueryRepositoryListSpecitication
import javax.inject.Inject

class SimpleMovieTypeQueryRepositoryListSpeciticationFactory @Inject constructor(): MovieTypeQueryRepositoryListSpeciticationFactory {
    override fun newListSpec(): MovieTypeQueryRepositoryListSpecitication
            = SimpleMovieTypeQueryRepositoryListSpecitication()
}