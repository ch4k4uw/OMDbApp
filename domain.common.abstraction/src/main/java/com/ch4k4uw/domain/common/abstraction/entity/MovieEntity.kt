package com.ch4k4uw.domain.common.abstraction.entity

import com.ch4k4uw.domain.abstraction.entity.LongIdEntity
import com.ch4k4uw.domain.common.abstraction.value.MovieActorValue
import com.ch4k4uw.domain.common.abstraction.value.MovieRatingValue
import java.util.*

interface MovieEntity: LongIdEntity {
    val id2: String

    val title: String

    val year: String

    val rated: String

    val released: Calendar

    val runtime: String

    val genre: String

    val director: String

    val writer: String

    val actors: List<MovieActorValue>

    val plot: String

    val language: String

    val country: String

    val awards: String

    val posterUri: String

    val ratings: List<MovieRatingValue>

    val metascore: String

    val imdbRating: Float

    val imdbVotes: Int

    val type: MovieTypeEntity

    val totalSeasons: Int

}