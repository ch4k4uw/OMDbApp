package com.ch4k4uw.infrastructure.factory.value

import com.ch4k4uw.domain.common.abstraction.factory.value.CommonValueFactory
import com.ch4k4uw.domain.common.abstraction.value.MovieActorValue
import com.ch4k4uw.domain.common.abstraction.value.MovieRatingValue
import com.ch4k4uw.infrastructure.value.SimpleMovieActorValue
import com.ch4k4uw.infrastructure.value.SimpleMovieRatingValue
import javax.inject.Inject

class SimpleCommonValueFactory @Inject constructor(): CommonValueFactory {
    override val emptyMovieActorValue: MovieActorValue
        get() = SimpleMovieActorValue.Empty
    override val emptyMoviewRatingValue: MovieRatingValue
        get() = SimpleMovieRatingValue.Empty

    override fun newMovieActorValue(name: String): MovieActorValue
            = SimpleMovieActorValue(name)

    override fun newMovieRatingValue(source: String, value: String): MovieRatingValue
            = SimpleMovieRatingValue(source, value)

}