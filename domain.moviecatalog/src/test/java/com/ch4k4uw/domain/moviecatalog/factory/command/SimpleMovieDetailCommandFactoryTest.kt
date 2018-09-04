package com.ch4k4uw.domain.moviecatalog.factory.command

import com.ch4k4uw.domain.common.abstraction.factory.specification.MovieQueryRepositorySpecificationFactory
import com.ch4k4uw.domain.common.abstraction.repository.MovieQueryRepository
import com.ch4k4uw.domain.common.abstraction.repository.specification.MovieQueryRepositoryDetailSpecification
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SimpleMovieDetailCommandFactoryTest {
    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T

    @Mock
    lateinit var repository: MovieQueryRepository

    @Mock
    lateinit var specFactory: MovieQueryRepositorySpecificationFactory

    @Before
    fun setUp() {
        doAnswer {
            return@doAnswer mock(MovieQueryRepositoryDetailSpecification::class.java)
        }.`when`(specFactory).newMovieDetailSpec("1")
    }

    @Test
    fun newQuery() {
        SimpleMovieDetailCommandFactory(repository, specFactory)
                .newQuery("1")
                .exec({ }, { })

        verify(specFactory, atMost(1))
                .newMovieDetailSpec("1")

        verify(repository, atMost(1))
                .find(any(), any(), any())

    }
}