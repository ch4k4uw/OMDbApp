package com.ch4k4uw.infrastructure.abstraction.rest

data class MovieDetail(
        val Title: String?,
        val Year: String?,
        val Rated: String?,
        val Released: String?,
        val Runtime: String?,
        val Genre: String?,
        val Director: String?,
        val Writer: String?,
        val Actors: String?,
        val Plot: String?,
        val Language: String?,
        val Country: String?,
        val Awards: String?,
        val Poster: String?,
        val Ratings: List<Rating>?,
        val Metascore: String?,
        val imdbRating: String?,
        val imdbVotes: String?,
        val imdbID: String?,
        val Type: String?,
        val totalSeasons: String?,
        val Response: String?
)