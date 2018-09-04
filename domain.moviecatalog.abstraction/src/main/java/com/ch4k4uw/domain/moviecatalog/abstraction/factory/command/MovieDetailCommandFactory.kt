package com.ch4k4uw.domain.moviecatalog.abstraction.factory.command

import com.ch4k4uw.domain.abstraction.factory.command.LongIdCommandFactory
import com.ch4k4uw.domain.common.abstraction.entity.MovieEntity
import com.ch4k4uw.domain.moviecatalog.abstraction.command.MovieDetailQuery

interface MovieDetailCommandFactory: LongIdCommandFactory<MovieEntity> {
    fun newQuery(id2: String): MovieDetailQuery

}