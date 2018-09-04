package com.ch4k4uw.domain.moviecatalog.factory.command

import com.ch4k4uw.domain.common.abstraction.factory.specification.MovieQueryRepositorySpecificationFactory
import com.ch4k4uw.domain.common.abstraction.repository.MovieQueryRepository
import com.ch4k4uw.domain.moviecatalog.abstraction.command.MovieDetailQuery
import com.ch4k4uw.domain.moviecatalog.abstraction.factory.command.MovieDetailCommandFactory
import com.ch4k4uw.domain.moviecatalog.command.SimpleMovieDetailQuery
import javax.inject.Inject

class SimpleMovieDetailCommandFactory @Inject constructor(private val repository: MovieQueryRepository, private val specFactory: MovieQueryRepositorySpecificationFactory): MovieDetailCommandFactory {
    override fun newQuery(id2: String): MovieDetailQuery
            = SimpleMovieDetailQuery(id2, repository, specFactory)
}