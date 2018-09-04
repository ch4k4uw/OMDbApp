package com.ch4k4uw.domain.abstraction.scheduler

import io.reactivex.ObservableTransformer

interface SchedulerProvider {
    fun <T> applySchedulers(): ObservableTransformer<T, T>
}