package com.ch4k4uw.domain.common.abstraction.repository.specification

import com.ch4k4uw.domain.abstraction.repository.specification.LongIdRepositorySpecification
import com.ch4k4uw.domain.common.abstraction.entity.MovieEntity

interface MovieQueryRepositoryCatalogSpecification: LongIdRepositorySpecification<MovieEntity> {
    val title: String

    val typeId: Long?

    val year: Int?

    val page: Int

}