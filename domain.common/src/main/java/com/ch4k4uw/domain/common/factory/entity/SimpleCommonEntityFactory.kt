package com.ch4k4uw.domain.common.factory.entity

import com.ch4k4uw.domain.common.abstraction.entity.MovieEntity
import com.ch4k4uw.domain.common.abstraction.entity.MovieTypeEntity
import com.ch4k4uw.domain.common.abstraction.factory.entity.CommonEntityFactory
import com.ch4k4uw.domain.common.abstraction.value.MovieActorValue
import com.ch4k4uw.domain.common.abstraction.value.MovieRatingValue
import com.ch4k4uw.domain.common.entity.SimpleMovieEntity
import com.ch4k4uw.domain.common.entity.SimpleMovieTypeEntity
import java.util.*
import javax.inject.Inject

class SimpleCommonEntityFactory @Inject constructor(): CommonEntityFactory {
    override val emptyMovieTypeEntity: MovieTypeEntity
        get() = SimpleMovieTypeEntity.Empty

    override val emptyMovieEntity: MovieEntity
        get() = SimpleMovieEntity.Empty

    override fun newMovieEntity(id: Long, id2: String, title: String, year: String, type: MovieTypeEntity, posterUri: String): MovieEntity
            = SimpleMovieEntity(id, id2, title, year, type, posterUri)

    override fun newMovieEntity(id: Long, id2: String, title: String, year: String, rated: String, released: Calendar, runtime: String, genre: String, director: String, writer: String, actors: List<MovieActorValue>, plot: String, language: String, country: String, awards: String, posterUri: String, ratings: List<MovieRatingValue>, metascore: String, imdbRating: Float, imdbVotes: Int, type: MovieTypeEntity, totalSeasons: Int): MovieEntity
            = SimpleMovieEntity(id, id2, title, year, rated, released, runtime, genre, director, writer, actors, plot, language, country, awards, posterUri, ratings, metascore, imdbRating, imdbVotes, type, totalSeasons)

    override fun newMovieTypeEntity(id: Long, name: String): MovieTypeEntity
            = SimpleMovieTypeEntity(id, name)
}