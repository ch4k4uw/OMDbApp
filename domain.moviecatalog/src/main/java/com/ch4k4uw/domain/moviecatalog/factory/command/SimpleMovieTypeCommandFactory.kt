package com.ch4k4uw.domain.moviecatalog.factory.command

import com.ch4k4uw.domain.moviecatalog.abstraction.command.MovieTypeQuery
import com.ch4k4uw.domain.moviecatalog.abstraction.factory.command.MovieTypeCommandFactory
import com.ch4k4uw.domain.moviecatalog.abstraction.factory.specification.MovieTypeQueryRepositoryListSpeciticationFactory
import com.ch4k4uw.domain.moviecatalog.abstraction.repository.MovieTypeQueryRepository
import com.ch4k4uw.domain.moviecatalog.command.SimpleMovieTypeQuery
import javax.inject.Inject

class SimpleMovieTypeCommandFactory @Inject constructor(private val repository: MovieTypeQueryRepository, private val specFactory: MovieTypeQueryRepositoryListSpeciticationFactory): MovieTypeCommandFactory {
    override fun newQuery(): MovieTypeQuery
            = SimpleMovieTypeQuery(repository, specFactory)
}