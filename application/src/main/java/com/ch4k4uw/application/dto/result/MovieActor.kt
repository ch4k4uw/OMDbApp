package com.ch4k4uw.application.dto.result

import java.io.Serializable

class MovieActor(name: String): Serializable, Cloneable {
    constructor(): this("")

    companion object {
        val Empty = MovieActor("")
    }

    var name: String = name
        internal set

}

fun movieActor(block: MovieActor.() -> Unit): MovieActor = MovieActor().apply(block)