package com.ch4k4uw.domain.abstraction.factory.specification

import com.ch4k4uw.domain.abstraction.entity.Entity

interface ByIdRepositorySpecificationFactory<T, TId> where T: Entity<TId>