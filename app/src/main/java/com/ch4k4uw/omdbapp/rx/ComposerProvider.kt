package com.ch4k4uw.omdbapp.rx

import com.ch4k4uw.domain.abstraction.scheduler.SchedulerProvider
import io.reactivex.ObservableTransformer
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface ComposerProvider {
    fun stream(): Stream

    interface Stream {
        fun applyDebounce(milliseconds: Long): Stream

        fun applySchedulers(): Stream

        fun <T> apply(): ObservableTransformer<T, T>

    }

}

class SimpleComposerProvider @Inject constructor(private val schedulerProvider: SchedulerProvider): ComposerProvider {
    override fun stream(): ComposerProvider.Stream = object: ComposerProvider.Stream {
        private var debounce: Long? = null
        private var scheduler: Boolean = false

        override fun applyDebounce(milliseconds: Long): ComposerProvider.Stream {
            debounce = milliseconds
            return this
        }

        override fun applySchedulers(): ComposerProvider.Stream {
            scheduler = true
            return this
        }

        override fun <T> apply(): ObservableTransformer<T, T>
                = ObservableTransformer { upstream ->
                    if(scheduler) {
                        upstream.compose(schedulerProvider.applySchedulers())
                    }
                    if(debounce != null) {
                        upstream.debounce(debounce!!, TimeUnit.MILLISECONDS)
                    }
            upstream
                }

    }

}