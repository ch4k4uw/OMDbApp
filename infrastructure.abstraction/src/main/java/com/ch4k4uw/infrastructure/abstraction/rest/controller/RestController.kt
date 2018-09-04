package com.ch4k4uw.infrastructure.abstraction.rest.controller

import com.ch4k4uw.infrastructure.abstraction.rest.Movie
import com.ch4k4uw.infrastructure.abstraction.rest.MovieDetail
import com.ch4k4uw.infrastructure.abstraction.rest.MovieType

interface RestController {
    fun getMovieTypes(success: (List<MovieType>) -> Unit, error: (Throwable) -> Unit)

    fun getMovieType(code: Int, success: (MovieType) -> Unit, error: (Throwable) -> Unit)

    fun getMovieType(name: String, success: (MovieType) -> Unit, error: (Throwable) -> Unit)

    fun searchMovies(title: String, type: String?, year: String?, page: Int, success: (List<Movie>) -> Unit, error: (Throwable) -> Unit)

    fun getById(id: String, success: (MovieDetail) -> Unit, error: (Throwable) -> Unit)

}