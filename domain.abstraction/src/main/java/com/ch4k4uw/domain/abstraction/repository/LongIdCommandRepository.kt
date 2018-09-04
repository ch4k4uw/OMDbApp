package com.ch4k4uw.domain.abstraction.repository

import com.ch4k4uw.domain.abstraction.entity.LongIdEntity

interface LongIdCommandRepository<T>: CommandRepository<T, Long> where T: LongIdEntity