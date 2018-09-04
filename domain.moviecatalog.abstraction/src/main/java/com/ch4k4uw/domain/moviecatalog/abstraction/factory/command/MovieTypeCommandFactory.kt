package com.ch4k4uw.domain.moviecatalog.abstraction.factory.command

import com.ch4k4uw.domain.abstraction.factory.command.LongIdCommandFactory
import com.ch4k4uw.domain.common.abstraction.entity.MovieTypeEntity
import com.ch4k4uw.domain.moviecatalog.abstraction.command.MovieTypeQuery

interface MovieTypeCommandFactory: LongIdCommandFactory<MovieTypeEntity> {
    fun newQuery(): MovieTypeQuery

}