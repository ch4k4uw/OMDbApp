package com.ch4k4uw.application.service

import com.ch4k4uw.application.dto.result.MovieType
import com.ch4k4uw.application.dto.result.movieType
import com.ch4k4uw.domain.moviecatalog.abstraction.application.ListTypesApplicationService
import com.ch4k4uw.domain.moviecatalog.abstraction.factory.command.MovieTypeCommandFactory
import javax.inject.Inject

class SimpleListTypesService @Inject constructor(private val commandFactory: MovieTypeCommandFactory): ListTypesApplicationService<MovieType> {
    override fun list(success: (List<MovieType>) -> Unit, error: (Throwable) -> Unit)
            = commandFactory
                    .newQuery()
                    .exec({
                        val result = arrayListOf<MovieType>()
                        it.forEach { movieTypeEntity ->
                            result.add(
                                    movieType {
                                        id = movieTypeEntity.id
                                        name = movieTypeEntity.name
                                    }
                            )
                        }
                        success(result)
                    }, error)

}