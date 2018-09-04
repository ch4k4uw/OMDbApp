package com.ch4k4uw.infrastructure.abstraction.rest

data class Movie(
        val Title: String,
        val Year: String,
        val imdbID: String,
        val Type: String,
        val Poster: String
)