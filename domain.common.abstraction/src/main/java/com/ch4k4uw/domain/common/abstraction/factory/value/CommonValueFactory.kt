package com.ch4k4uw.domain.common.abstraction.factory.value

import com.ch4k4uw.domain.common.abstraction.value.MovieActorValue
import com.ch4k4uw.domain.common.abstraction.value.MovieRatingValue

interface CommonValueFactory {
    val emptyMovieActorValue: MovieActorValue

    val emptyMoviewRatingValue: MovieRatingValue

    fun newMovieActorValue(name: String): MovieActorValue

    fun newMovieRatingValue(source: String, value: String): MovieRatingValue

}