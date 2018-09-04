package com.ch4k4uw.domain.abstraction.factory.command

import com.ch4k4uw.domain.abstraction.entity.Entity

interface CommandFactory<T, TId> where T: Entity<TId>