package com.ch4k4uw.domain.abstraction.repository.specification

import com.ch4k4uw.domain.abstraction.entity.LongIdEntity

interface LongIdRepositorySpecification<T>: RepositorySpecification<T, Long> where T: LongIdEntity