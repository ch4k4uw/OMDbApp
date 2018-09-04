package com.ch4k4uw.infrastructure.repository.specification

import com.ch4k4uw.domain.common.abstraction.repository.specification.MovieQueryRepositoryCatalogSpecification

class SimpleMovieQueryRepositoryCatalogSpecification(
        override val title: String,
        override val typeId: Long?,
        override val year: Int?,
        override val page: Int) : MovieQueryRepositoryCatalogSpecification