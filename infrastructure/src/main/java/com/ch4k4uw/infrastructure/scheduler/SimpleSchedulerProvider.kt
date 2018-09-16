package com.ch4k4uw.infrastructure.scheduler

import com.ch4k4uw.domain.abstraction.scheduler.SchedulerProvider
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SimpleSchedulerProvider {
    companion object {
        @JvmStatic
        val IoScheduler: SchedulerProvider = object : SchedulerProvider {
            override fun <T> applySchedulers(): ObservableTransformer<T, T> =
                    ObservableTransformer { upstream ->
                        upstream
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                    }

        }

        @JvmStatic
        val ComputationScheduler: SchedulerProvider = object : SchedulerProvider {
            override fun <T> applySchedulers(): ObservableTransformer<T, T> =
                    ObservableTransformer { upstream ->
                        upstream
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.computation())
                    }

        }

        @JvmStatic
        val AndroidScheduler: SchedulerProvider = object : SchedulerProvider {
            override fun <T> applySchedulers(): ObservableTransformer<T, T> =
                    ObservableTransformer { upstream ->
                        upstream
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(AndroidSchedulers.mainThread())
                    }

        }
    }

    class SimpleSchedulerProviderChooser @Inject constructor(private val type: String?): SchedulerProvider {
        override fun <T> applySchedulers(): ObservableTransformer<T, T> =
                ObservableTransformer { upstream ->
                    upstream.compose(when (type) {
                        "io" -> SimpleSchedulerProvider.IoScheduler.applySchedulers()
                        "droid" -> SimpleSchedulerProvider.AndroidScheduler.applySchedulers()
                        "comp" -> SimpleSchedulerProvider.ComputationScheduler.applySchedulers()
                        else -> SimpleSchedulerProvider.IoScheduler.applySchedulers()
                    })
                }

    }

    open class SimpleAndroidSchedulerProvider @Inject constructor(): SchedulerProvider {
        override fun <T> applySchedulers(): ObservableTransformer<T, T> =
                ObservableTransformer { upstream ->
                    upstream
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(AndroidSchedulers.mainThread())
                }

    }

    class SimpleIOSchedulerProvider @Inject constructor(): SchedulerProvider {
        override fun <T> applySchedulers(): ObservableTransformer<T, T> =
                ObservableTransformer { upstream ->
                    upstream
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                }

    }
}