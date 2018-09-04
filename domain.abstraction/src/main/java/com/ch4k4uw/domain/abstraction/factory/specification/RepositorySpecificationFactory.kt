package com.ch4k4uw.domain.abstraction.factory.specification

import com.ch4k4uw.domain.abstraction.entity.Entity

interface RepositorySpecificationFactory<T, TId> where T: Entity<TId>