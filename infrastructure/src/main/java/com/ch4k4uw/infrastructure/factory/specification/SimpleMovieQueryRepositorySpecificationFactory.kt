package com.ch4k4uw.infrastructure.factory.specification

import com.ch4k4uw.domain.common.abstraction.factory.specification.MovieQueryRepositorySpecificationFactory
import com.ch4k4uw.domain.common.abstraction.repository.specification.MovieQueryRepositoryCatalogSpecification
import com.ch4k4uw.domain.common.abstraction.repository.specification.MovieQueryRepositoryDetailSpecification
import com.ch4k4uw.infrastructure.repository.specification.SimpleMovieQueryRepositoryCatalogSpecification
import com.ch4k4uw.infrastructure.repository.specification.SimpleMovieQueryRepositoryDetailSpecification
import javax.inject.Inject

class SimpleMovieQueryRepositorySpecificationFactory @Inject constructor() : MovieQueryRepositorySpecificationFactory {
    override fun newCatalogSpec(title: String, typeId: Long?, year: Int?, page: Int): MovieQueryRepositoryCatalogSpecification
            = SimpleMovieQueryRepositoryCatalogSpecification(title, typeId, year, page)

    override fun newMovieDetailSpec(id2: String): MovieQueryRepositoryDetailSpecification
            = SimpleMovieQueryRepositoryDetailSpecification(id2)

}