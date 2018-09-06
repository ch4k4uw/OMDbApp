package com.ch4k4uw.application.dto.result

import java.io.Serializable

class Rating(source: String, value: String): Serializable, Cloneable {
    constructor(): this("", "")

    var source: String = source
        internal set

    var value: String = value
        internal set

}

fun rating(block: Rating.() -> Unit): Rating = Rating().apply(block)