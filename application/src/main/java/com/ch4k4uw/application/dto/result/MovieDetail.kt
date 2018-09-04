package com.ch4k4uw.application.dto.result

import java.io.Serializable
import java.util.*

class MovieDetail(title: String,
                  year: String,
                  rated: String,
                  released: Calendar,
                  runtime: String,
                  genre: String,
                  director: String,
                  writer: String,
                  actors: List<MovieActor>,
                  plot: String,
                  language: String,
                  country: String,
                  awards: String,
                  posterUri: String,
                  ratings: List<Rating>,
                  metascore: String,
                  imdbRating: Float,
                  imdbVotes: Int,
                  type: MovieType,
                  totalSeasons: Int): Serializable, Cloneable {
    constructor(): this("", "", "", EmptyDate, "", "", "", "", listOf(), "", "", "", "", "", listOf(), "", 0f, 0, MovieType.Empty, 0)

    companion object {
        private val EmptyDate = Calendar.getInstance()
        init {
            EmptyDate.timeInMillis = 0
        }
    }

    private val mDate: Calendar = released

    var title: String = title
        internal set
    var year: String = year
        internal set
    var rated: String = rated
        internal set
    var released: Calendar
        get() {
            val curr = Calendar.getInstance()
            curr.timeInMillis = mDate.timeInMillis

            return curr
        }
        internal set(value) {
            mDate.timeInMillis = value.timeInMillis
        }
    var runtime: String = runtime
        internal set
    var genre: String = genre
        internal set
    var director: String = director
        internal set
    var writer: String = writer
        internal set
    var actors: List<MovieActor> = actors
        internal set
    var plot: String = plot
        internal set
    var language: String = language
        internal set
    var country: String = country
        internal set
    var awards: String = awards
        internal set
    var posterUri: String = posterUri
        internal set
    var ratings: List<Rating> = ratings
        internal set
    var metascore: String = metascore
        internal set
    var imdbRating: Float = imdbRating
        internal set
    var imdbVotes: Int = imdbVotes
        internal set
    var type: MovieType = type
        internal set
    var totalSeasons: Int = totalSeasons
        internal set

}

fun movieDetail(block: MovieDetail.() -> Unit): MovieDetail = MovieDetail().apply(block)