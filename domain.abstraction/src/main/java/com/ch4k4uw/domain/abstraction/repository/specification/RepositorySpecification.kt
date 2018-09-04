package com.ch4k4uw.domain.abstraction.repository.specification

import com.ch4k4uw.domain.abstraction.entity.Entity

interface RepositorySpecification<T, TId> where T: Entity<TId>