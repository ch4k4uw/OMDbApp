package com.ch4k4uw.infrastructure.repository

import com.ch4k4uw.domain.common.abstraction.entity.MovieEntity
import com.ch4k4uw.domain.common.abstraction.repository.MovieQueryRepository
import com.ch4k4uw.infrastructure.factory.entity.SimpleCommonEntityFactory
import com.ch4k4uw.infrastructure.factory.value.SimpleCommonValueFactory
import com.ch4k4uw.infrastructure.repository.specification.SimpleMovieQueryRepositoryCatalogSpecification
import com.ch4k4uw.infrastructure.repository.specification.SimpleMovieQueryRepositoryDetailSpecification
import com.ch4k4uw.infrastructure.rest.controller.SimpleRestController
import com.ch4k4uw.infrastructure.scheduler.SimpleSchedulerProvider
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [23])
class SimpleMovieQueryRepositoryTest {
    private lateinit var repository: MovieQueryRepository

    @Before
    fun setUp() {
        val restController = SimpleRestController("http://www.omdbapi.com", "d7b91e80", SimpleSchedulerProvider.AndroidScheduler)
        repository = SimpleMovieQueryRepository(SimpleSchedulerProvider.AndroidScheduler, restController, SimpleCommonEntityFactory(), SimpleCommonValueFactory())

    }

    @Test
    fun getById() {
        val spec = SimpleMovieQueryRepositoryDetailSpecification("tt0147746")

        var entity: MovieEntity? = null
        var error: Throwable? = null

        repository.getById(spec, { entity = it }, { error = it })

        assertNull("Shouldn't have error", error)
        assertNotNull("Should have entity", entity)

    }

    @Test
    fun find() {
        val spec1 = SimpleMovieQueryRepositoryCatalogSpecification("Batman", null, null, 1)
        val spec2 = SimpleMovieQueryRepositoryCatalogSpecification("Batman", 2L, null, 1)
        val spec3 = SimpleMovieQueryRepositoryCatalogSpecification("Batman", 2L, 1999, 1)
        val spec4 = SimpleMovieQueryRepositoryCatalogSpecification("Batman", null, 1999, 1)

        var results: List<MovieEntity>? = null
        var error: Throwable? = null

        repository.find(spec1, { results = it }, { error = it })

        assertNull("Shouldn't have error", error)
        assertNotNull("Should have results", results)

        results = null

        repository.find(spec2, { results = it }, { error = it })

        assertNull("Shouldn't have error", error)
        assertNotNull("Should have results", results)

        results = null

        repository.find(spec3, { results = it }, { error = it })

        assertNull("Shouldn't have error", error)
        assertNotNull("Should have results", results)

        results = null

        repository.find(spec4, { results = it }, { error = it })

        assertNull("Shouldn't have error", error)
        assertNotNull("Should have results", results)

    }
}