package com.ch4k4uw.domain.moviecatalog.abstraction.command

import com.ch4k4uw.domain.abstraction.command.LongIdListQuery
import com.ch4k4uw.domain.common.abstraction.entity.MovieTypeEntity

interface MovieTypeQuery: LongIdListQuery<MovieTypeEntity>