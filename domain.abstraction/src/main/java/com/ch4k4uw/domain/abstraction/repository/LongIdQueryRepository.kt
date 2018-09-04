package com.ch4k4uw.domain.abstraction.repository

import com.ch4k4uw.domain.abstraction.entity.LongIdEntity

interface LongIdQueryRepository<T>: QueryRepository<T, Long> where T: LongIdEntity