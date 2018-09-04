package com.ch4k4uw.infrastructure.repository

import com.ch4k4uw.domain.abstraction.repository.specification.ByIdRepositorySpecification
import com.ch4k4uw.domain.common.abstraction.entity.MovieTypeEntity
import com.ch4k4uw.domain.moviecatalog.abstraction.repository.MovieTypeQueryRepository
import com.ch4k4uw.infrastructure.factory.entity.SimpleCommonEntityFactory
import com.ch4k4uw.infrastructure.repository.specification.SimpleMovieTypeQueryRepositoryListSpecitication
import com.ch4k4uw.infrastructure.rest.controller.SimpleRestController
import com.ch4k4uw.infrastructure.scheduler.SimpleSchedulerProvider
import com.google.gson.reflect.TypeToken
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class SimpleMovieTypeQueryRepositoryTest {
    private lateinit var repository: MovieTypeQueryRepository

    @Before
    fun setUp() {
        val restController = SimpleRestController("http://www.omdbapi.com", "d7b91e80", SimpleSchedulerProvider.AndroidScheduler)
        repository = SimpleMovieTypeQueryRepository(restController, SimpleCommonEntityFactory())

    }

    @Test
    fun getById() {
    }

    @Test
    fun find() {
        val spec = SimpleMovieTypeQueryRepositoryListSpecitication()

        var result: List<MovieTypeEntity>? = null
        var error: Throwable? = null

        repository.find(spec, { result = it }, { error = it })

        assertNull("Shouldn't have error", error)
        assertNotNull("Should have result", result)

    }
}