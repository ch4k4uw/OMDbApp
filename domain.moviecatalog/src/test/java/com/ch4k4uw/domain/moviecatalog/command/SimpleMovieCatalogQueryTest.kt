package com.ch4k4uw.domain.moviecatalog.command

import com.ch4k4uw.domain.common.abstraction.factory.specification.MovieQueryRepositorySpecificationFactory
import com.ch4k4uw.domain.common.abstraction.repository.MovieQueryRepository
import com.ch4k4uw.domain.common.abstraction.repository.specification.MovieQueryRepositoryCatalogSpecification
import com.ch4k4uw.domain.moviecatalog.abstraction.command.MovieCatalogQuery
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SimpleMovieCatalogQueryTest {
    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T

    private lateinit var query: MovieCatalogQuery

    @Mock
    lateinit var repository: MovieQueryRepository

    @Mock
    lateinit var specFactory: MovieQueryRepositorySpecificationFactory

    @Before
    fun setUp() {
        doAnswer {
            return@doAnswer mock(MovieQueryRepositoryCatalogSpecification::class.java)
        }.`when`(specFactory).newCatalogSpec(anyString(), anyInt())

        query = SimpleMovieCatalogQuery("1", 2, repository, specFactory)

    }

    @Test
    fun exec() {
        query.exec({ }, { })

        verify(specFactory, atMost(1))
                .newCatalogSpec("1", 2)

        verify(repository, atMost(1))
                .find(any(), any(), any())

    }
}