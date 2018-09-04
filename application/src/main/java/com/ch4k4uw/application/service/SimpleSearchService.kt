package com.ch4k4uw.application.service

import com.ch4k4uw.application.dto.result.Movie
import com.ch4k4uw.application.dto.result.movie
import com.ch4k4uw.application.dto.result.movieType
import com.ch4k4uw.domain.common.abstraction.application.SearchApplicationService
import com.ch4k4uw.domain.common.abstraction.entity.MovieEntity
import com.ch4k4uw.domain.moviecatalog.abstraction.factory.command.MovieCatalogCommandFactory
import javax.inject.Inject

class SimpleSearchService @Inject constructor(private val commandFactory: MovieCatalogCommandFactory): SearchApplicationService<Movie> {
    override fun search(title: String, page: Int, success: (List<Movie>) -> Unit, error: (Throwable) -> Unit) =
            commandFactory
                    .newQuery(title, null, null, page)
                    .exec({
                        success(parse(it))
                    }, error)


    override fun search(title: String, typeId: Long, page: Int, success: (List<Movie>) -> Unit, error: (Throwable) -> Unit) =
            commandFactory
                    .newQuery(title, typeId, null, page)
                    .exec({
                        success(parse(it))
                    }, error)

    override fun search(title: String, year: Int, page: Int, success: (List<Movie>) -> Unit, error: (Throwable) -> Unit) =
            commandFactory
                    .newQuery(title, null, year, page)
                    .exec({
                        success(parse(it))
                    }, error)

    override fun search(title: String, typeId: Long, year: Int, page: Int, success: (List<Movie>) -> Unit, error: (Throwable) -> Unit) =
            commandFactory
                    .newQuery(title, typeId, year, page)
                    .exec({
                        success(parse(it))
                    }, error)

    private fun parse(entities: List<MovieEntity>): List<Movie> = entities.map { parse(it) }
    private fun parse(entity: MovieEntity): Movie = movie {
        id = entity.id
        title = entity.title
        year = entity.year
        id2 = entity.id2
        type = movieType {
            id = entity.type.id
            name = entity.type.name
        }
        poster = entity.posterUri
    }
}