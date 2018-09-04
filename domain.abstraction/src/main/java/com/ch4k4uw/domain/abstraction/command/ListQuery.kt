package com.ch4k4uw.domain.abstraction.command

import com.ch4k4uw.domain.abstraction.entity.Entity

/**
 *
 */
interface ListQuery<T, TId> where T: Entity<TId> {
    /**
     *
     */
    fun exec(success: (List<T>) -> Unit, error: (Throwable) -> Unit)
}