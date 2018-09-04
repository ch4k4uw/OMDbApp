package com.ch4k4uw.domain.common.abstraction.value

import com.ch4k4uw.domain.abstraction.value.Value

interface MovieRatingValue: Value {
    val source: String

    val value: String

}