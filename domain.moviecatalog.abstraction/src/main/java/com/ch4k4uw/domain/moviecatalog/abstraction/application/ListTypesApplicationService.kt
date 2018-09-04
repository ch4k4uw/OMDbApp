package com.ch4k4uw.domain.moviecatalog.abstraction.application

import java.io.Serializable

interface ListTypesApplicationService<T> where T: Serializable, T: Cloneable {
    fun list(success: (List<T>) -> Unit, error: (Throwable) -> Unit)

}