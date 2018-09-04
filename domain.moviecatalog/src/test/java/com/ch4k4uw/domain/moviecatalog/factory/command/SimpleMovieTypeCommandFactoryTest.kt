package com.ch4k4uw.domain.moviecatalog.factory.command

import com.ch4k4uw.domain.moviecatalog.abstraction.command.MovieTypeQuery
import com.ch4k4uw.domain.moviecatalog.abstraction.factory.specification.MovieTypeQueryRepositoryListSpeciticationFactory
import com.ch4k4uw.domain.moviecatalog.abstraction.repository.MovieTypeQueryRepository
import com.ch4k4uw.domain.moviecatalog.abstraction.repository.specification.MovieTypeQueryRepositoryListSpecitication
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SimpleMovieTypeCommandFactoryTest {
    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T

    @Mock
    lateinit var repository: MovieTypeQueryRepository

    @Mock
    lateinit var specFactory: MovieTypeQueryRepositoryListSpeciticationFactory

    @Before
    fun setUp() {
        doAnswer {
            return@doAnswer mock(MovieTypeQueryRepositoryListSpecitication::class.java)
        }.`when`(specFactory).newListSpec()
    }

    @Test
    fun newQuery() {
        SimpleMovieTypeCommandFactory(repository, specFactory)
                .newQuery()
                .exec({ }, { })

        verify(specFactory, atMost(1))
                .newListSpec()

        verify(repository, atMost(1))
                .find(any(), any(), any())
    }
}