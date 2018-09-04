package com.ch4k4uw.domain.abstraction.factory.specification

import com.ch4k4uw.domain.abstraction.entity.LongIdEntity

interface ByLongIdRepositorySpecificationFactory<T>: ByIdRepositorySpecificationFactory<T, Long> where T: LongIdEntity