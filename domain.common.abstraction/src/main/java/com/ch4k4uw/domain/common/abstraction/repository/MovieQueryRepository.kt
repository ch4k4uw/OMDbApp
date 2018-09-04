package com.ch4k4uw.domain.common.abstraction.repository

import com.ch4k4uw.domain.abstraction.repository.LongIdQueryRepository
import com.ch4k4uw.domain.common.abstraction.entity.MovieEntity

interface MovieQueryRepository: LongIdQueryRepository<MovieEntity>