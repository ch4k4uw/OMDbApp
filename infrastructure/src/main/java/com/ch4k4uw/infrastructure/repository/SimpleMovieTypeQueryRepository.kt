package com.ch4k4uw.infrastructure.repository

import com.ch4k4uw.domain.abstraction.repository.specification.ByIdRepositorySpecification
import com.ch4k4uw.domain.abstraction.repository.specification.RepositorySpecification
import com.ch4k4uw.domain.common.abstraction.entity.MovieTypeEntity
import com.ch4k4uw.domain.common.abstraction.factory.entity.CommonEntityFactory
import com.ch4k4uw.domain.moviecatalog.abstraction.repository.MovieTypeQueryRepository
import com.ch4k4uw.domain.moviecatalog.abstraction.repository.specification.MovieTypeQueryRepositoryListSpecitication
import com.ch4k4uw.infrastructure.abstraction.rest.controller.RestController
import java.security.InvalidParameterException
import javax.inject.Inject

class SimpleMovieTypeQueryRepository @Inject constructor(
        private val restController: RestController,
        private val entityFactory: CommonEntityFactory): MovieTypeQueryRepository {
    override fun getById(spec: ByIdRepositorySpecification<MovieTypeEntity, Long>, success: (MovieTypeEntity) -> Unit, error: (Throwable) -> Unit) {
        success(entityFactory.emptyMovieTypeEntity)
    }

    override fun find(spec: RepositorySpecification<MovieTypeEntity, Long>, success: (List<MovieTypeEntity>) -> Unit, error: (Throwable) -> Unit) {
        if(spec is MovieTypeQueryRepositoryListSpecitication) {
            restController.getMovieTypes({ rawMovieTypes ->
                val result = arrayListOf<MovieTypeEntity>()
                rawMovieTypes.forEach {
                    result.add(entityFactory.newMovieTypeEntity(it.id.toLong(), it.name))
                }

                success(result)

            }, error)
        } else {
            throw InvalidParameterException()
        }
    }
}