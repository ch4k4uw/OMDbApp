package com.ch4k4uw.domain.common.value

import com.ch4k4uw.domain.abstraction.value.Value
import com.ch4k4uw.domain.common.abstraction.value.MovieActorValue

class SimpleMovieActorValue(override val name: String) : MovieActorValue {

    companion object {
        val Empty = object: MovieActorValue {
            override fun compareTo(other: Value): Int
                    = name.compareTo((other as MovieActorValue).name)

            override val name: String
                get() = ""

        }
    }

    override fun compareTo(other: Value): Int
            = name.compareTo((other as MovieActorValue).name)
}