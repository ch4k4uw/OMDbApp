package com.ch4k4uw.domain.moviecatalog.factory.command

import com.ch4k4uw.domain.common.abstraction.factory.specification.MovieQueryRepositorySpecificationFactory
import com.ch4k4uw.domain.common.abstraction.repository.MovieQueryRepository
import com.ch4k4uw.domain.common.abstraction.repository.specification.MovieQueryRepositoryCatalogSpecification
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SimpleMovieCatalogCommandFactoryTest {
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
        Mockito.doAnswer {
            return@doAnswer Mockito.mock(MovieQueryRepositoryCatalogSpecification::class.java)
        }.`when`(specFactory).newCatalogSpec(anyString(), anyInt())

    }

    @Test
    fun newQuery() {
        SimpleMovieCatalogCommandFactory(repository, specFactory)
                .newQuery("1", 2)
                .exec({}, {})

        Mockito.verify(specFactory, Mockito.atMost(1))
                .newCatalogSpec("1", 2)

        Mockito.verify(repository, Mockito.atMost(1))
                .find(any(), any(), any())

    }
}