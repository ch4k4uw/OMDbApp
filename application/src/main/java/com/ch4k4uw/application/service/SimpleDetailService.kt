package com.ch4k4uw.application.service

import com.ch4k4uw.application.dto.result.*
import com.ch4k4uw.domain.common.abstraction.application.DetailApplicationService
import com.ch4k4uw.domain.moviecatalog.abstraction.factory.command.MovieDetailCommandFactory
import javax.inject.Inject

class SimpleDetailService @Inject constructor(private val commandFactory: MovieDetailCommandFactory): DetailApplicationService<MovieDetail> {
    override fun detail(id2: String, success: (MovieDetail) -> Unit, error: (Throwable) -> Unit) = commandFactory.newQuery(id2).exec({
        movieDetail {
            title = it.title
            year = it.year
            rated = it.rated
            released = it.released
            runtime = it.runtime
            genre = it.genre
            director = it.director
            writer = it.writer
            actors = it.actors.map { rawActor ->
                movieActor {
                    name = rawActor.name
                }
            }
            plot = it.plot
            language = it.language
            country = it.country
            awards = it.awards
            posterUri = it.posterUri
            ratings = it.ratings.map { rawRating ->
                rating {
                    source = rawRating.source
                    value = rawRating.value
                }
            }
            metascore = it.metascore
            imdbRating = it.imdbRating
            imdbVotes = it.imdbVotes
            type = movieType {
                id = it.type.id
                name = it.type.name
            }
            totalSeasons = it.totalSeasons
        }
    }, error)

}