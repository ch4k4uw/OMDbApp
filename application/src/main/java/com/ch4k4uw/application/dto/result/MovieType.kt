package com.ch4k4uw.application.dto.result

import java.io.Serializable

class MovieType(id: Long, name: String): Serializable, Cloneable {
    companion object {
        internal val Empty = MovieType(0, "")
    }

    constructor(): this(0, "")

    var id: Long = id
        internal set

    var name: String = name
        internal set

}

fun movieType(block: MovieType.() -> Unit): MovieType
        = MovieType().apply(block)