package com.ch4k4uw.domain.abstraction.repository.specification

import com.ch4k4uw.domain.abstraction.entity.Entity

interface ByIdRepositorySpecification<T, TId> where T: Entity<TId>