package com.ch4k4uw.domain.abstraction.factory.command

import com.ch4k4uw.domain.abstraction.entity.LongIdEntity

interface LongIdCommandFactory<T>: CommandFactory<T, Long> where T: LongIdEntity