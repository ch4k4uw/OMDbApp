package com.ch4k4uw.domain.common.abstraction.application

import java.io.Serializable

interface SearchApplicationService<T> where T: Serializable, T: Cloneable {
    fun search(title: String, typeId: Long?, year: Int?, page: Int, success: (List<T>) -> Unit, error: (Throwable) -> Unit)
}