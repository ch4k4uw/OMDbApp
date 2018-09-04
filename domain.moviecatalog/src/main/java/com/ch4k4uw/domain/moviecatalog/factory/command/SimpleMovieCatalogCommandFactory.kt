package com.ch4k4uw.domain.moviecatalog.factory.command

import com.ch4k4uw.domain.common.abstraction.factory.specification.MovieQueryRepositorySpecificationFactory
import com.ch4k4uw.domain.common.abstraction.repository.MovieQueryRepository
import com.ch4k4uw.domain.moviecatalog.abstraction.command.MovieCatalogQuery
import com.ch4k4uw.domain.moviecatalog.abstraction.factory.command.MovieCatalogCommandFactory
import com.ch4k4uw.domain.moviecatalog.command.SimpleMovieCatalogQuery
import javax.inject.Inject

class SimpleMovieCatalogCommandFactory @Inject constructor(private val repository: MovieQueryRepository, private val specFactory: MovieQueryRepositorySpecificationFactory): MovieCatalogCommandFactory {
    override fun newQuery(title: String, typeId: Long?, year: Int?, page: Int): MovieCatalogQuery
            = SimpleMovieCatalogQuery(title, typeId, year, page, repository, specFactory)
}