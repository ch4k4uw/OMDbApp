package com.ch4k4uw.infrastructure.entity

import com.ch4k4uw.domain.common.abstraction.entity.MovieTypeEntity

class SimpleMovieTypeEntity(override val id: Long, override val name: String) : MovieTypeEntity {
    companion object {
        val Empty = object: MovieTypeEntity {
            override val name: String
                get() = ""
            override val id: Long
                get() = 0L

        }
    }
}