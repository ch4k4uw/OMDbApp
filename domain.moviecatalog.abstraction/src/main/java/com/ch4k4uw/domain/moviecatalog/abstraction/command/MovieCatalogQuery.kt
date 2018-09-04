package com.ch4k4uw.domain.moviecatalog.abstraction.command

import com.ch4k4uw.domain.abstraction.command.LongIdListQuery
import com.ch4k4uw.domain.common.abstraction.entity.MovieEntity

interface MovieCatalogQuery: LongIdListQuery<MovieEntity> {
    val title: String

    val typeId: Long?

    val year: Int?

    val page: Int

}