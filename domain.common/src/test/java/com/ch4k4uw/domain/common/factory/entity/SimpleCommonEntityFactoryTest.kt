package com.ch4k4uw.domain.common.factory.entity

import com.ch4k4uw.domain.common.factory.value.SimpleCommonValueFactory
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class SimpleCommonEntityFactoryTest {
    private val entityFactory = SimpleCommonEntityFactory()
    private val valueFactory = SimpleCommonValueFactory()

    @Before
    fun setUp() {
    }

    @Test
    fun getEmptyMovieTypeEntity() {
        val entity = entityFactory.emptyMovieTypeEntity

        assertEquals(0L, entity.id)
        assertEquals("", entity.name)

    }

    @Test
    fun getEmptyMovieEntity() {
        val entity = entityFactory.emptyMovieEntity

        assertEquals(0L, entity.id)
        assertEquals("", entity.id2)
        assertEquals("", entity.title)
        assertEquals("", entity.year)
        assertEquals("", entity.rated)
        assertEquals(0, entity.released.timeInMillis)
        assertEquals("", entity.runtime)
        assertEquals("", entity.genre)
        assertEquals("", entity.director)
        assertEquals("", entity.writer)
        assertEquals(0, entity.actors.size)
        assertEquals("", entity.plot)
        assertEquals("", entity.language)
        assertEquals("", entity.country)
        assertEquals("", entity.awards)
        assertEquals("", entity.posterUri)
        assertEquals(0, entity.ratings.size)
        assertEquals("", entity.metascore)
        assertEquals(0f, entity.imdbRating)
        assertEquals(0, entity.imdbVotes)
        assertEquals(0L, entity.type.id)
        assertEquals("", entity.type.name)
        assertEquals(0, entity.totalSeasons)
    }

    @Test
    fun newMovieEntity() {
        val type = entityFactory.newMovieTypeEntity(5L, "6")
        val entity = entityFactory.newMovieEntity(1, "2", "3", "4", type, "7")

        assertEquals(1L, entity.id)
        assertEquals("2", entity.id2)
        assertEquals("3", entity.title)
        assertEquals("4", entity.year)
        assertEquals(5L, entity.type.id)
        assertEquals("6", entity.type.name)
        assertEquals("7", entity.posterUri)

    }

    @Test
    fun newMovieEntity1() {
        val type = entityFactory.newMovieTypeEntity(1L, "2")
        val ratings = listOf(valueFactory.newMovieRatingValue("1", "2"))
        val actors = listOf(valueFactory.newMovieActorValue("1"))
        val date = Calendar.getInstance()

        date.timeInMillis = 0

        val entity = entityFactory.newMovieEntity(1L, "2", "3", "4", "5", date, "6", "7", "8", "9", actors, "10", "11", "12", "13", "14", ratings, "15", 16f, 17, type, 18)

        assertEquals(1L, entity.id)
        assertEquals("2", entity.id2)
        assertEquals("3", entity.title)
        assertEquals("4", entity.year)
        assertEquals("5", entity.rated)
        assertEquals(0, entity.released.timeInMillis)
        assertEquals("6", entity.runtime)
        assertEquals("7", entity.genre)
        assertEquals("8", entity.director)
        assertEquals("9", entity.writer)
        assertEquals("1", entity.actors[0].name)
        assertEquals("10", entity.plot)
        assertEquals("11", entity.language)
        assertEquals("12", entity.country)
        assertEquals("13", entity.awards)
        assertEquals("14", entity.posterUri)
        assertEquals("1", entity.ratings[0].source)
        assertEquals("2", entity.ratings[0].value)
        assertEquals("15", entity.metascore)
        assertEquals(16f, entity.imdbRating)
        assertEquals(17, entity.imdbVotes)
        assertEquals(1L, entity.type.id)
        assertEquals("2", entity.type.name)
        assertEquals(18, entity.totalSeasons)
    }

    @Test
    fun newMovieTypeEntity() {
        val entity = entityFactory.newMovieTypeEntity(1L, "2")

        assertEquals(1L, entity.id)
        assertEquals("2", entity.name)
    }
}