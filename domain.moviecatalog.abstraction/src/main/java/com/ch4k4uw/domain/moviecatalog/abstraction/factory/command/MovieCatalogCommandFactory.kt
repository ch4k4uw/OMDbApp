package com.ch4k4uw.domain.moviecatalog.abstraction.factory.command

import com.ch4k4uw.domain.abstraction.factory.command.LongIdCommandFactory
import com.ch4k4uw.domain.common.abstraction.entity.MovieEntity
import com.ch4k4uw.domain.moviecatalog.abstraction.command.MovieCatalogQuery

interface MovieCatalogCommandFactory: LongIdCommandFactory<MovieEntity> {
    fun newQuery(title: String, typeId: Long?, year: Int?, page: Int): MovieCatalogQuery

}