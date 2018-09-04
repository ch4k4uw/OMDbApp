package com.ch4k4uw.domain.abstraction.command

import com.ch4k4uw.domain.abstraction.entity.LongIdEntity

/**
 *
 */
interface LongIdListQuery<T>: ListQuery<T, Long> where T: LongIdEntity