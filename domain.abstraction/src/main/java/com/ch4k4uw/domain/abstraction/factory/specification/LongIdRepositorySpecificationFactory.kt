package com.ch4k4uw.domain.abstraction.factory.specification

import com.ch4k4uw.domain.abstraction.entity.LongIdEntity

interface LongIdRepositorySpecificationFactory<T>: RepositorySpecificationFactory<T, Long> where T: LongIdEntity