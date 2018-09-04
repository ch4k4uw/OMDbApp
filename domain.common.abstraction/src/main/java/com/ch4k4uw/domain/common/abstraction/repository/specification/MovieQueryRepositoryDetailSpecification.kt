package com.ch4k4uw.domain.common.abstraction.repository.specification

import com.ch4k4uw.domain.abstraction.repository.specification.ByLongIdRepositorySpecification
import com.ch4k4uw.domain.common.abstraction.entity.MovieEntity

interface MovieQueryRepositoryDetailSpecification: ByLongIdRepositorySpecification<MovieEntity> {
    val id2: String
}