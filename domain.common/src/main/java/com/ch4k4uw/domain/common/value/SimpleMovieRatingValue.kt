package com.ch4k4uw.domain.common.value

import com.ch4k4uw.domain.abstraction.value.Value
import com.ch4k4uw.domain.common.abstraction.value.MovieRatingValue

class SimpleMovieRatingValue(override val source: String, override val value: String) : MovieRatingValue {

    companion object {

        val Empty = object: MovieRatingValue {
            override val source: String
                get() = ""
            override val value: String
                get() = ""

            override fun compareTo(other: Value): Int = this - (other as MovieRatingValue)

        }
    }

    override fun compareTo(other: Value): Int = this - (other as MovieRatingValue)
}

private operator fun MovieRatingValue.minus(other: MovieRatingValue): Int = when {
    source.compareTo(other.source) != 0 -> source.compareTo(other.source)
    source.compareTo(other.value) != 0 -> source.compareTo(other.value)
    else -> 0
}