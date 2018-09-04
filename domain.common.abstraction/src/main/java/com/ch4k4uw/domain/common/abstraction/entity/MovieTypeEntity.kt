package com.ch4k4uw.domain.common.abstraction.entity

import com.ch4k4uw.domain.abstraction.entity.LongIdEntity

interface MovieTypeEntity: LongIdEntity {
    val name: String

}