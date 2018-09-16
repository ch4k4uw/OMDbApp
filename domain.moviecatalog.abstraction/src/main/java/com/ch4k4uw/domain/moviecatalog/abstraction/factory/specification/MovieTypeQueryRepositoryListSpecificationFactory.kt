package com.ch4k4uw.domain.moviecatalog.abstraction.factory.specification

import com.ch4k4uw.domain.abstraction.factory.specification.LongIdRepositorySpecificationFactory
import com.ch4k4uw.domain.common.abstraction.entity.MovieTypeEntity
import com.ch4k4uw.domain.moviecatalog.abstraction.repository.specification.MovieTypeQueryRepositoryListSpecitication

interface MovieTypeQueryRepositoryListSpecificationFactory: LongIdRepositorySpecificationFactory<MovieTypeEntity> {
    fun newListSpec(): MovieTypeQueryRepositoryListSpecitication

}