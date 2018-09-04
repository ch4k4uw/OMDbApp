package com.ch4k4uw.domain.common.abstraction.application

import java.io.Serializable

interface DetailApplicationService<T> where T: Serializable, T: Cloneable {
    fun detail(id2: String, success: (T) -> Unit, error: (Throwable) -> Unit)

}