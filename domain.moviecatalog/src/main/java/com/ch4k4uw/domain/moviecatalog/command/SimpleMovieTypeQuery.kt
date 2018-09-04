package com.ch4k4uw.domain.moviecatalog.command

import com.ch4k4uw.domain.common.abstraction.entity.MovieTypeEntity
import com.ch4k4uw.domain.moviecatalog.abstraction.command.MovieTypeQuery
import com.ch4k4uw.domain.moviecatalog.abstraction.factory.specification.MovieTypeQueryRepositoryListSpeciticationFactory
import com.ch4k4uw.domain.moviecatalog.abstraction.repository.MovieTypeQueryRepository

class SimpleMovieTypeQuery(private val repository: MovieTypeQueryRepository, private val specFactory: MovieTypeQueryRepositoryListSpeciticationFactory): MovieTypeQuery {
    override fun exec(success: (List<MovieTypeEntity>) -> Unit, error: (Throwable) -> Unit) {
        repository.find(specFactory.newListSpec(), success, error)
    }
}