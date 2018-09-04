package com.ch4k4uw.domain.moviecatalog.command

import com.ch4k4uw.domain.common.abstraction.entity.MovieEntity
import com.ch4k4uw.domain.common.abstraction.factory.specification.MovieQueryRepositorySpecificationFactory
import com.ch4k4uw.domain.common.abstraction.repository.MovieQueryRepository
import com.ch4k4uw.domain.moviecatalog.abstraction.command.MovieDetailQuery

class SimpleMovieDetailQuery(override val id2: String, private val repository: MovieQueryRepository, private val specFactory: MovieQueryRepositorySpecificationFactory) : MovieDetailQuery {
    override fun exec(success: (MovieEntity) -> Unit, error: (Throwable) -> Unit) {
        repository.getById(specFactory.newMovieDetailSpec(id2), success, error)
    }
}