package com.ch4k4uw.domain.moviecatalog.abstraction.command

import com.ch4k4uw.domain.abstraction.command.LongIdQuery
import com.ch4k4uw.domain.common.abstraction.entity.MovieEntity

interface MovieDetailQuery: LongIdQuery<MovieEntity> {
    val id2: String

}