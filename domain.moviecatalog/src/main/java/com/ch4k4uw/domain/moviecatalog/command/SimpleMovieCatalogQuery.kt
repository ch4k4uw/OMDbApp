package com.ch4k4uw.domain.moviecatalog.command

import com.ch4k4uw.domain.common.abstraction.entity.MovieEntity
import com.ch4k4uw.domain.common.abstraction.factory.specification.MovieQueryRepositorySpecificationFactory
import com.ch4k4uw.domain.common.abstraction.repository.MovieQueryRepository
import com.ch4k4uw.domain.moviecatalog.abstraction.command.MovieCatalogQuery

class SimpleMovieCatalogQuery(
        override val title: String,
        override val typeId: Long?,
        override val year: Int?,
        override val page: Int,
        private val repository: MovieQueryRepository,
        private val specFactory: MovieQueryRepositorySpecificationFactory) : MovieCatalogQuery {
    override fun exec(success: (List<MovieEntity>) -> Unit, error: (Throwable) -> Unit) {
        repository
                .find(specFactory.newCatalogSpec(title, typeId, year, page), success, error)
    }
}