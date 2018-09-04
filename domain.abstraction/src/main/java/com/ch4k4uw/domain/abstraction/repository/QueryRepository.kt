package com.ch4k4uw.domain.abstraction.repository

import com.ch4k4uw.domain.abstraction.entity.Entity
import com.ch4k4uw.domain.abstraction.repository.specification.ByIdRepositorySpecification
import com.ch4k4uw.domain.abstraction.repository.specification.RepositorySpecification

/**
 *
 */
interface QueryRepository<T, TId> where T: Entity<TId> {
    /**
     *
     */
    fun getById(spec: ByIdRepositorySpecification<T, TId>, success: (T) -> Unit, error: (Throwable) -> Unit)

    /**
     *
     */
    fun find(spec: RepositorySpecification<T, TId>, success: (List<T>) -> Unit, error: (Throwable) -> Unit)

}