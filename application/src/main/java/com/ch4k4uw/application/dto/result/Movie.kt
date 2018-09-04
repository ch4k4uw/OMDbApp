package com.ch4k4uw.application.dto.result

import java.io.Serializable

class Movie(id: Long,
            title: String,
            year: String,
            imdbID: String,
            type: MovieType,
            poster: String): Serializable, Cloneable {
    constructor(): this(0, "", "", "", MovieType.Empty, "")

    var id: Long = id
        internal set
    var title: String = title
        internal set
    var year: String = year
        internal set
    var id2: String = imdbID
        internal set
    var type: MovieType = type
        internal set
    var poster: String = poster
        internal set

}

fun movie(block: Movie.() -> Unit): Movie
        = Movie().apply(block)