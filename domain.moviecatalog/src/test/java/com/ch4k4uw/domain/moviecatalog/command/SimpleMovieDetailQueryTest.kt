package com.ch4k4uw.domain.moviecatalog.command

import com.ch4k4uw.domain.common.abstraction.factory.specification.MovieQueryRepositorySpecificationFactory
import com.ch4k4uw.domain.common.abstraction.repository.MovieQueryRepository
import com.ch4k4uw.domain.common.abstraction.repository.specification.MovieQueryRepositoryDetailSpecification
import com.ch4k4uw.domain.moviecatalog.abstraction.command.MovieDetailQuery
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.atMost
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SimpleMovieDetailQueryTest {
    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T

    private lateinit var query: MovieDetailQuery

    @Mock
    lateinit var repository: MovieQueryRepository

    @Mock
    lateinit var specFactory: MovieQueryRepositorySpecificationFactory

    @Before
    fun setUp() {
        Mockito.doAnswer {
            return@doAnswer Mockito.mock(MovieQueryRepositoryDetailSpecification::class.java)
        }.`when`(specFactory).newMovieDetailSpec(anyString())

        query = SimpleMovieDetailQuery("1", repository, specFactory)
    }

    @Test
    fun exec() {
        query.exec({ }, { })

        verify(specFactory, atMost(1))
                .newMovieDetailSpec("1")

        verify(repository, atMost(1))
                .find(any(), any(), any())
    }
}