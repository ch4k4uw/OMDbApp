package com.ch4k4uw.domain.common.abstraction.factory.entity

import com.ch4k4uw.domain.common.abstraction.entity.MovieEntity
import com.ch4k4uw.domain.common.abstraction.entity.MovieTypeEntity
import com.ch4k4uw.domain.common.abstraction.value.MovieActorValue
import com.ch4k4uw.domain.common.abstraction.value.MovieRatingValue
import java.util.*

interface CommonEntityFactory {
    val emptyMovieTypeEntity: MovieTypeEntity

    val emptyMovieEntity: MovieEntity

    fun newMovieEntity(id: Long, id2: String, title: String, year: String, type: MovieTypeEntity, posterUri: String): MovieEntity

    fun newMovieEntity(
            id: Long,
            id2: String,
            title: String,
            year: String,
            rated: String,
            released: Calendar,
            runtime: String,
            genre: String,
            director: String,
            writer: String,
            actors: List<MovieActorValue>,
            plot: String,
            language: String,
            country: String,
            awards: String,
            posterUri: String,
            ratings: List<MovieRatingValue>,
            metascore: String,
            imdbRating: Float,
            imdbVotes: Int,
            type: MovieTypeEntity,
            totalSeasons: Int): MovieEntity

    fun newMovieTypeEntity(id: Long, name: String): MovieTypeEntity

}