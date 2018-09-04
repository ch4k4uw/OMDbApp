package com.ch4k4uw.infrastructure.entity

import com.ch4k4uw.domain.common.abstraction.entity.MovieEntity
import com.ch4k4uw.domain.common.abstraction.entity.MovieTypeEntity
import com.ch4k4uw.domain.common.abstraction.value.MovieActorValue
import com.ch4k4uw.domain.common.abstraction.value.MovieRatingValue
import java.util.*

class SimpleMovieEntity (
        override val id: Long,
        override val id2: String,
        override val title: String,
        override val year: String,
        override val rated: String,
        override val released: Calendar,
        override val runtime: String,
        override val genre: String,
        override val director: String,
        override val writer: String,
        override val actors: List<MovieActorValue>,
        override val plot: String,
        override val language: String,
        override val country: String,
        override val awards: String,
        override val posterUri: String,
        override val ratings: List<MovieRatingValue>,
        override val metascore: String,
        override val imdbRating: Float,
        override val imdbVotes: Int,
        override val type: MovieTypeEntity,
        override val totalSeasons: Int) : MovieEntity {

    companion object {
        private val emptyMovieType = object: MovieTypeEntity {
            override val name: String
                get() = ""
            override val id: Long
                get() = 0L

        }

        val Empty = object: MovieEntity {
            override val id2: String
                get() = ""
            override val title: String
                get() = ""
            override val year: String
                get() = ""
            override val rated: String
                get() = ""
            override val released: Calendar
                get() {
                    val result = Calendar.getInstance()
                    result.timeInMillis = 0

                    return result
                }
            override val runtime: String
                get() = ""
            override val genre: String
                get() = ""
            override val director: String
                get() = ""
            override val writer: String
                get() = ""
            override val actors: List<MovieActorValue>
                get() = listOf()
            override val plot: String
                get() = ""
            override val language: String
                get() = ""
            override val country: String
                get() = ""
            override val awards: String
                get() = ""
            override val posterUri: String
                get() = ""
            override val ratings: List<MovieRatingValue>
                get() = listOf()
            override val metascore: String
                get() = ""
            override val imdbRating: Float
                get() = 0f
            override val imdbVotes: Int
                get() = 0
            override val type: MovieTypeEntity
                get() = emptyMovieType
            override val totalSeasons: Int
                get() = 0
            override val id: Long
                get() = 0L

        }

    }

    constructor(id: Long, id2: String, title: String, year: String, type: MovieTypeEntity, posterUri: String)
            : this(
            id,
            id2,
            title,
            year,
            "",
            Empty.released,
            "",
            "",
            "",
            "",
            listOf(),
            "",
            "",
            "",
            "",
            posterUri,
            listOf(),
            "",
            0f,
            0,
            type,
            0)

}