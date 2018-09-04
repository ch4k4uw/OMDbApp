package com.ch4k4uw.domain.common.abstraction.factory.specification

import com.ch4k4uw.domain.abstraction.factory.specification.LongIdRepositorySpecificationFactory
import com.ch4k4uw.domain.common.abstraction.entity.MovieEntity
import com.ch4k4uw.domain.common.abstraction.repository.specification.MovieQueryRepositoryCatalogSpecification
import com.ch4k4uw.domain.common.abstraction.repository.specification.MovieQueryRepositoryDetailSpecification

interface MovieQueryRepositorySpecificationFactory: LongIdRepositorySpecificationFactory<MovieEntity> {
    fun newCatalogSpec(title: String, typeId: Long?, year: Int?, page: Int = 1): MovieQueryRepositoryCatalogSpecification

    fun newMovieDetailSpec(id2: String): MovieQueryRepositoryDetailSpecification

}